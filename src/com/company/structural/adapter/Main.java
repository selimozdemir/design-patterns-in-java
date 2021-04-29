package com.company.structural.adapter;

class ExcelDocument{

}

class Excel2003Document {

}

interface Excel2003Reader {
    Excel2003Document read();
}

interface ExcelReader {
    ExcelDocument read();
}

class Excel2003Adapter implements ExcelReader {
    Excel2003Reader e;
    public Excel2003Adapter(Excel2003Reader e) {
        this.e = e;
    }

    @Override
    public ExcelDocument read() {
        return convertTo2007();
    }

    ExcelDocument convertTo2007(){
        return new ExcelDocument();
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
