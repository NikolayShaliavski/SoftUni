package core;

import interfaces.*;
import interfaces.Runnable;

import java.io.IOException;

public class Engine implements Runnable{

    private BlobsData blobsData;
    private Factory factory;
    private Reader reader;
    private Writer writer;

    public Engine(BlobsData blobsData,
                  Factory factory,
                  Reader reader,
                  Writer writer) {
        this.blobsData = blobsData;
        this.factory = factory;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run() throws IOException, ReflectiveOperationException {

        String line = this.reader.read();

        while (!line.equals("drop")) {
            String[] params = line.split(" ");
            switch (params[0]) {
                case "report-events":
                    this.blobsData.setReportValues();
                    break;
                case "create":
                    this.blobsData.update();
                    Blob newBlob = this.factory.createBlob(params);
                    this.blobsData.addBlob(newBlob);
                    break;
                case "attack":
                    String attackerName = params[1];
                    String targetName = params[2];
                    String result = this.blobsData.fight(attackerName, targetName);
                    if (!result.equals("")) {
                        this.writer.write(result);
                    }
                    break;
                case "status":
                    this.writer.write(this.blobsData.status());
                    this.blobsData.update();
                    break;
                case "pass":
                    this.blobsData.update();
                    break;
            }
            line = this.reader.read();
        }
    }
}
