package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import java.security.NoSuchAlgorithmException;
import java.util.Set;

public interface IBlock {

    void setBlockId(long blockId);
    long getBlockId();
    boolean addTransaction(ITransaction transaction);
    Set<ITransaction> getAllTransactions();
    String getHash() throws NoSuchAlgorithmException;

    long getEndTime();
}
