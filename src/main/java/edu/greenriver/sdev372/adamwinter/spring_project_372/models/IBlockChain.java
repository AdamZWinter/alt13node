package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import java.util.List;

public interface IBlockChain {

    int addBlock(IBlock block);
    IBlock getBlockbyId(int id);
    int getLatestBlockId();
    List<IBlock> getAllBlocks();
    long getEndTime();
    IBlock getCurrentBlock();

}
