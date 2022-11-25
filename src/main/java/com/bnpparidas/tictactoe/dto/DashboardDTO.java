package com.bnpparidas.tictactoe.dto;

public class DashboardDTO {

    char[] firstRow ;
    char[] secondRow ;
    char[] thirdRow;


    public char[] getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(char[] firstRow) {
        this.firstRow = firstRow;
    }

    public char[] getSecondRow() {
        return secondRow;
    }

    public void setSecondRow(char[] secondRow) {
        this.secondRow = secondRow;
    }

    public char[] getThirdRow() {
        return thirdRow;
    }

    public void setThirdRow(char[] thirdRow) {
        this.thirdRow = thirdRow;
    }
}
