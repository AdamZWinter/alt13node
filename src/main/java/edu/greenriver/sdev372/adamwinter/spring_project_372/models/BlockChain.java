package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BlockChain implements IBlockChain {

    List<IBlock> listOfBlocks;

    public BlockChain(List<IBlock> listOfBlocks, IBlock genesisBlock) {
        this.listOfBlocks = listOfBlocks;
        addBlock(genesisBlock);
    }

    public int addBlock(IBlock block){
        if(listOfBlocks.add(block)){
            int blockId = listOfBlocks.size() - 1;
            block.setBlockId(blockId);
            Set<ITransaction> transactionSet = block.getAllTransactions();
            for (ITransaction transaction : transactionSet) {
                transaction.setBlockId(blockId);
            }
            return blockId;
        }else{
            return -1;
        }
    }

    public long getEndTime(){
        IBlock block = listOfBlocks.get(0);
        return block.getEndTime();
    }

    public IBlock getCurrentBlock(){
        IBlock currentBlock = listOfBlocks.get(0);
        return currentBlock;
    }

    public IBlock getBlockbyId(int id){
        return listOfBlocks.get(id);
    }

    public int getLatestBlockId(){
        return listOfBlocks.size() - 1;
    }

    public List<IBlock> getAllBlocks(){
        return listOfBlocks;
    }

    @Override
    public String toString() {
        return "BlockChain{" +
                "listOfBlocks=" + listOfBlocks +
                '}';
    }
}
