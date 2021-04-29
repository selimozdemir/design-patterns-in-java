package com.company.creational.factory;

interface Processor {

}

class IntelX86 implements Processor {

}

class IntelX86_64 implements Processor {

}


interface Modem {

}

class Broadcomm implements Modem {

}

class Realtek implements Modem {

}

interface Camera {

}

class LeicaMonochrome implements Camera {

}

class Samsung implements Camera {

}

interface GPU {

}

class Mali implements GPU {

}

class Nvidia implements GPU {

}

interface Display {

}

class Amoled implements Display {

}

interface AbstractPhoneFactory {
    Processor createProcessor();
    Modem createModem();
    Camera createCamera();
    GPU createGPU();
    Display createDisplay();


}

class HuaweiP30 implements AbstractPhoneFactory {

    @Override
    public Processor createProcessor() {
        return new IntelX86_64();
    }

    @Override
    public Modem createModem() {
        return new Broadcomm();
    }

    @Override
    public Camera createCamera() {
        return new LeicaMonochrome();
    }

    @Override
    public GPU createGPU() {
        return new Nvidia();
    }

    @Override
    public Display createDisplay() {
        return new Amoled();
    }
}

class HuaweiP20 implements AbstractPhoneFactory{

    @Override
    public Processor createProcessor() {
        return new IntelX86();
    }

    @Override
    public Modem createModem() {
        return new Realtek();
    }

    @Override
    public Camera createCamera() {
        return new Samsung();
    }

    @Override
    public GPU createGPU() {
        return new Mali();
    }

    @Override
    public Display createDisplay() {
        return new Amoled();
    }
}

class Supplier {
    AbstractPhoneFactory factory;


    public Supplier(AbstractPhoneFactory factory) {
        this.factory = factory;
    }

    void supply(){
        Processor p = this.factory.createProcessor();
        Modem m = this.factory.createModem();

    }
}

public class Main {
    enum models { HuaweiP30, HuaweiP20 };

    public static void main(String[] args) {

        if(args.length < 1){
            System.out.println("Please provide a model");
            System.exit(0);
        }

        String model = args[0];

        if(model.equals(models.HuaweiP20)){
            Supplier s = new Supplier(new HuaweiP30());
            s.supply();

        }


    }


}
