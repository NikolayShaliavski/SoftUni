package core;

import interfaces.Blob;
import interfaces.BlobsData;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlobsRepository implements BlobsData {

    private Map<String, Blob> blobs;
    private boolean report;

    public BlobsRepository() {
        this.blobs = new LinkedHashMap<>();
    }

    @Override
    public void setReportValues() {
        this.report = true;
    }

    @Override
    public void update() {

        for (Blob blob : this.blobs.values()) {
            blob.update();
        }
    }

    @Override
    public void addBlob(Blob newBlob) {
        this.blobs.put(newBlob.getName(), newBlob);
    }

    @Override
    public String fight(String attackerName, String targetName) {
        StringBuilder result = new StringBuilder();

        Blob source = this.blobs.get(attackerName);
        Blob target = this.blobs.get(targetName);

        if (source.getHealth() > 0 && target.getHealth() > 0) {
            source.attack(target);
        }

        if (this.report) {
            for (Blob blob : this.blobs.values()) {
                String blobInfo = blob.update();
                if (!blobInfo.equals("")) {
                    result.append(blobInfo);
                    result.append(System.lineSeparator());
                }
            }

        } else {
            this.update();
        }

        if (target.getHealth() <= 0 && this.report) {
            result.append(String.format("Blob %s was killed", target.getName()));
        }
        return result.toString().trim();
    }

    @Override
    public String status() {
        StringBuilder builder = new StringBuilder();
        for (Blob blob : this.blobs.values()) {
            builder.append(blob.toString());
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
