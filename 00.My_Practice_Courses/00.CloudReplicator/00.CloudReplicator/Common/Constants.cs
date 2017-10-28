namespace CloudReplicator.Common
{
    public static class Constants
    {
        public const string CloudTypeAzure = "Azure";
        public const string CloudTypeS3 = "S3";

        public const string RootParentId = "00000000000000000000000000000000";

        public const string AmazonMetaNameKey = "x-amz-meta-name";
        public const string AmazonMetaCreationDateKey = "x-amz-meta-crtime";
        public const string AmazonMetaAccessDateKey = "x-amz-meta-acctime";
        public const string AmazonMetaFlagKey = "x-amz-meta-flags";

        public const string AzureMetaNameKey = "name";
        public const string AzureMetaCreationDateKey = "crtime";
        public const string AzureMetaAccessDateKey = "acctime";
        public const string AzureMetaFlagKey = "flags";
        public const string AzureSizeFlagKey = "size";

        public const string DataKeyPrefix = ".tt_rt/data/";
        public const string MetaKeyPrefix = ".tt_rt/meta/";
    }
}
