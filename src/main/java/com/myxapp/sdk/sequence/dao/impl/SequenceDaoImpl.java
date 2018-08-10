package com.myxapp.sdk.sequence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.myxapp.sdk.sequence.dao.ISequenceDao;
import com.myxapp.sdk.sequence.model.Sequence;
import com.myxapp.sdk.sequence.model.SequenceCache;
import com.myxapp.sdk.util.StringUtil;

public class SequenceDaoImpl implements ISequenceDao {

    private static final Logger LOG = LoggerFactory.getLogger(SequenceDaoImpl.class);

     private static final String sqlInsert = "insert into sys_sequences (SEQUENCE_NAME, START_BY, INCREMENT_BY, LAST_NUMBER, JVM_STEP_BY) values( ?, '1', '1', '0', '1')";
    private static final String sqlUpdate = "update sys_sequences set LAST_NUMBER=? where SEQUENCE_NAME = ? and LAST_NUMBER=?";

    private static final String queryAllSequenceSql = "select SEQUENCE_NAME,LAST_NUMBER from sys_sequences";

    private static final String sqlSelect = "select SEQUENCE_NAME,LAST_NUMBER,JVM_STEP_BY,INCREMENT_BY from sys_sequences where SEQUENCE_NAME = ?";

    private DataSource db;

    public SequenceDaoImpl(DataSource db) {
        this.db = db;
    }

    @Override
    public List<Sequence> queryAllSequence() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Sequence> sequenceList = null;
        try {
            conn = db.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(queryAllSequenceSql);
            rs = ps.executeQuery();
            if (rs != null) {
                String sequenceName = null;
                Long sequenceValue = null;
                Sequence sequence = null;
                sequenceList = new ArrayList<Sequence>();
                while (rs.next()) {
                    sequenceName = rs.getString("SEQUENCE_NAME");
                    sequenceValue = rs.getLong("LAST_NUMBER");
                    sequence = new Sequence();
                    sequence.setValue(sequenceValue);
                    sequence.setSequenceName(sequenceName);
                    sequenceList.add(sequence);
                }
                rs.close();
            }
            ps.close();
        } catch (SQLException e) {
            LOG.error("query all sequence  failed", e);
            throw new RuntimeException("query all sequence  failed", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOG.error("ResultSet close error", e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOG.error("Statement close error", e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error("connection close error", e);
                }
            }
        }
        return sequenceList;
    }

    @Override
    public SequenceCache getSequenceCache(String sequenceName) {
        LOG.debug("从数据库中获取sequenceCache");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            conn.setAutoCommit(false);
            for(int i=0;i<10;i++){
            	ps = conn.prepareStatement(sqlSelect);
            	ps.setString(1, sequenceName);
            	rs = ps.executeQuery();
            	if (rs != null && rs.next()) {
            		long currVal = rs.getLong("LAST_NUMBER");
            		long jvmStepBy = rs.getLong("JVM_STEP_BY");
            		rs.close();
            		ps.close();
            		ps = conn.prepareStatement(sqlUpdate);
            		ps.setLong(1, currVal + jvmStepBy);
            		ps.setString(2, sequenceName);
            		ps.setLong(3, currVal);
            		int ret = ps.executeUpdate();
            		conn.commit();
            		ps.close();
            		if (ret == 1) {
            			SequenceCache cache = new SequenceCache(currVal + 1, currVal + jvmStepBy);
            			
            			LOG.debug("get sequence cache:" + JSON.toJSONString(cache).toString());
            			return cache;
            		}
            		else{
            			try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							LOG.error("get sequence cache error:" + e.getMessage(),e);
						}
            		}
            	}// end if rs
            	
            }// end for 10
            
        } catch (SQLException e) {
            LOG.error("get sequence cache failed", e);
            throw new RuntimeException("get sequence cache failed", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOG.error("ResultSet close error", e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOG.error("Statement close error", e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error("connection close error", e);
                }
            }
        }
        return null;
    }

    @Override
    public Sequence querySequenceByName(String sequenceName) {
        if (StringUtil.isBlank(sequenceName)) {
            return null;
        }
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Sequence sequence = null;
        try {
            conn = db.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sqlSelect);
            ps.setString(1, sequenceName);
            rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                String name = rs.getString("SEQUENCE_NAME");
                long currVal = rs.getLong("LAST_NUMBER");
                sequence = new Sequence();
                sequence.setSequenceName(name);
                sequence.setValue(currVal);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            LOG.error("get sequence cache failed", e);
            throw new RuntimeException("get sequence cache failed", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOG.error("ResultSet close error", e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOG.error("Statement close error", e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error("connection close error", e);
                }
            }
        }
        return sequence;
    }

    @Override
    public void modifySequence(String sequenceName, long nextval) {
    	boolean updateResult=false;
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            conn.setAutoCommit(false);
            for(int i=0;i<10;i++){
	            ps = conn.prepareStatement(sqlSelect);
	        	ps.setString(1, sequenceName);
	        	rs = ps.executeQuery();
	        	if (rs != null && rs.next()) {
	        		long currVal = rs.getLong("LAST_NUMBER");
	        		//long jvmStepBy = rs.getLong("JVM_STEP_BY");
	        		rs.close();
	        		ps.close();
	        		
	        		ps = conn.prepareStatement(sqlUpdate);
	        		ps.setLong(1, nextval);
	        		ps.setString(2, sequenceName);
	        		ps.setLong(3, currVal);//防止高并发时，数据不一致
	        		int count = ps.executeUpdate();
	        		conn.commit();
	        		ps.close();
	        		if (count == 1) {
	        			//更新序列成功
	        			updateResult=true;
	        			break;
	        		}
	        		else{
            			try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							LOG.error("modify sequence cache error:" + e.getMessage(),e);
						}
            		}
	        		
	        	}
	        	else{
	        		//序列不存在
	        		throw new RuntimeException("modify sequence,the sequence name is " + sequenceName
	                        + " not exists!");
	        		
	        	}
            }// end for 10
            
            if(!updateResult){
            	throw new RuntimeException("modify sequence failed,the sequence name is " + sequenceName);
            }
            
        } catch (Exception e) {
            LOG.error("modify sequence failed,the detail message is ：" + e.getMessage());
            throw new RuntimeException("modify sequence failed!", e);
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOG.error("ResultSet close error", e);
                }
            }
        	
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOG.error("Statement close error", e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error("connection close error", e);
                }
            }
        }// finally
        
        

    }

    public DataSource getDb() {
        return db;
    }

    public void setDb(DataSource db) {
        this.db = db;
    }

	@Override
	public Sequence createSequenceByName(String sequenceName) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, sequenceName);
            int count = ps.executeUpdate();
            conn.commit();
            ps.close();
            if (count != 1) {
            	Sequence sequence = this.querySequenceByName(sequenceName);
            	if(sequence==null){
            		//insert failed
            		throw new RuntimeException("insert sequence failed, update data count="+count);
            	}
            	return sequence;
            }
            Sequence sequence = this.querySequenceByName(sequenceName);
            return sequence;
        } catch (Exception e) {
            LOG.error("insert sequence failed,the detail message is ：" + e.getMessage());
            throw new RuntimeException("insert sequence failed!", e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOG.error("Statement close error", e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error("connection close error", e);
                }
            }
        }
	}

}
