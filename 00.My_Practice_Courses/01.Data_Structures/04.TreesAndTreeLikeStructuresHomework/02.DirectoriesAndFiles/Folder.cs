using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class Folder
{
    public string Name { get; set; }

    public File[] Files { get; set; }

    public Folder[] Folders { get; set; }

    public Folder(string name)
    {
        this.Name = name;
    }
}
