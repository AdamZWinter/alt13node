package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import java.util.LinkedList;
import java.util.List;

public class BlockChain implements IBlockChain {

    List<IBlock> listOfBlocks;

    public BlockChain(List<IBlock> listOfBlocks) {
        this.listOfBlocks = listOfBlocks;
    }

    public int addBlock(IBlock block){
        if(listOfBlocks.add(block)){
            return listOfBlocks.size() - 1;
        }else{
            return -1;
        }
    }
}
