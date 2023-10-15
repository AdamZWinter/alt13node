package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import java.util.LinkedList;
import java.util.List;

public class BlockChain implements IBlockChain {

    List<Block> listOfBlocks;

    public BlockChain(List<Block> listOfBlocks) {
        this.listOfBlocks = listOfBlocks;
    }


}
