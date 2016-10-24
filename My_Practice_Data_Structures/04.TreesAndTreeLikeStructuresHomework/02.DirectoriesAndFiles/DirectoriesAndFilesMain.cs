using System;
using System.IO;
using System.Linq;

class DirectoriesAndFilesMain
{
    static void Main(string[] args)
    {
        DirectoryInfo rootDir = new DirectoryInfo("D:\\Movies");
        string dirName = rootDir.Name;
        Folder rootFolder = new Folder(dirName);

        TraverseDirectories(rootDir, rootFolder);

        TraverseFolders(rootFolder, null, 0);
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
