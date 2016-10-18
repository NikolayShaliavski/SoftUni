package interfaces;

public interface BlobsData {

    void update();

    void addBlob(Blob newBlob);

    String fight(String attackerName, String targetName);

    void setReportValues();

    String status();
}
