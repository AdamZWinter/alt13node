package edu.greenriver.sdev372.adamwinter.spring_project_372.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements ITransaction{

    @Getter
    @Setter
    private int blockId;
    @Getter
    @Setter
    private String body;
    @Getter
    @Setter
    private String signature;

    public Transaction(String body, String signature) {
        this.body = body;
        this.signature = signature;
        this.blockId = 0;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "blockId=" + blockId +
                ", body='" + body + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
