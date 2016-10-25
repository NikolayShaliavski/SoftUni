using System;
using System.IO;
using System.Linq;

class DirectoriesAndFilesMain
{
    private static bool hasFoundSubfolder;
    private static long sumFiles = 0;

    static void Main(string[] args)
    {
        DirectoryInfo rootDir = new DirectoryInfo("C:\\Windows");
        string dirName = rootDir.Name;
        Folder rootFolder = new Folder(dirName);

        TraverseDirectories(rootDir, rootFolder);

        string subfolderName = "AppPatch";
        
        SumOfFilesInSubfolder(rootFolder, subfolderName);
        Console.WriteLine("Sum of files in subtree: {0}", sumFiles);
        //TraverseFolders(rootFolder, null, 0);
    }

    private static void SumOfFilesInSubfolder(Folder folder, string subfolderName)
    {
        if (folder.Name == subfolderName)
        {
            hasFoundSubfolder = true;
        }
        if (hasFoundSubfolder)
        {
            if (folder.Files != null)
            {
                sumFiles += folder.Files.Select(f => f.Size).Sum();
            }
        }
        if (folder.Folders != null)
        {
            foreach (var f in folder.Folders)
            {
                SumOfFilesInSubfolder(f, subfolderName);
            }
        }
        
        if (folder.Name == subfolderName)
        {
            hasFoundSubfolder = false;
        }
        
    }

    private static void TraverseFolders(Folder folder, File file, int indent)
    {
        Console.Write(new string(' ', 2 * indent));
        if (folder != null)
        {
            Console.WriteLine(folder.Name);
        }
        if (file != null)
        {
            Console.WriteLine(file.Name);
        }
        if (folder != null)
        {
            foreach (var f in folder.Folders)
            {
                TraverseFolders(f, null, indent + 1);
            }
            foreach (var f in folder.Files)
            {
                TraverseFolders(null, f, indent + 1);
            }
        }      
    }

    private static void TraverseDirectories(DirectoryInfo dir, Folder folder)
    {
        try
        {
            folder.Files = dir.GetFiles().Select(file => new File(file.Name, file.Length)).ToArray();
            folder.Folders = dir.GetDirectories().Select(d => new Folder(d.Name)).ToArray();
            DirectoryInfo[] directories = dir.GetDirectories();

            for (int i = 0; i < directories.Length; i++)
            {
                TraverseDirectories(directories[i], folder.Folders[i]);
            }
        }
        catch (UnauthorizedAccessException)
        {
            
        }
    }
}
