package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * A list of blocks where each latest block includes the hash of the previous block
 */
public class BlockChain implements IBlockChain {

    List<IBlock> listOfBlocks;

    /**
     * Constructor
     * @param listOfBlocks Instantiate the blockchain by
     */
    public BlockChain(List<IBlock> listOfBlocks) {
        this.listOfBlocks = listOfBlocks;
    }

    /**
     * Append a block to the chain
     * @param block an IBlock
     * @return The blockId of the newly appended block
     * or -1 if that fails
     */
    public int addBlock(IBlock block){
        if(listOfBlocks.add(block)){
            int blockId = listOfBlocks.size();
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

    /**
     * Get the temporal end of the last block added to the chain
     * @return the temporal end of the last block added to the chain
     */
    public long getEndTime(){
        IBlock block = listOfBlocks.get(listOfBlocks.size()-1);
        return block.getEndTime();
    }

    /**
     * Get the most recent block added to the chain
     * @return IBlock the most recent block added to the chain
     */
    public IBlock getCurrentBlock(){
        IBlock currentBlock = listOfBlocks.get(listOfBlocks.size()-1);
        return currentBlock;
    }

    /**
     * Get a block by its Id
     * @param id the Id of the block
     * @return the block requested by Id
     * @throws IndexOutOfBoundsException
     */
    public IBlock getBlockbyId(int id){
        return listOfBlocks.get(id);
    }  //Throws IndexOutOfBoundsException

    /**
     * Get the block Id of the latest block added to the chain
     * @return the Id of the latest block added to the chain
     */
    public int getLatestBlockId(){
        return listOfBlocks.size() - 1;
    }

    /**
     * Get a List of all the block in the chain
     * @return List of all the blocks in the chain
     */
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
