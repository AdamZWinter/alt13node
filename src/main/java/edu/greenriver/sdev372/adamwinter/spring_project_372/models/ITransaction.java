package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

public interface ITransaction {
    void setBlockId(int blockId);
    int getBlockId();

    void setBody(String body);
    String getBody();

    void setSignature(String signature);
    String getSignature();

    //long getUTime();
}
