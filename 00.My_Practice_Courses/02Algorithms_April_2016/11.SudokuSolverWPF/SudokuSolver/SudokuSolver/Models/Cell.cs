using System.ComponentModel;

namespace SudokuSolver.Models
{
    public class Cell : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;

        private int? value;
        public int? Value
        {
            get { return this.value; }
            set
            {
                this.value = value;
                this.OnNotifyPropetyChanged("Value");
            }
        }
        public bool UserDefined { get; set; }

        private void OnNotifyPropetyChanged(string name)
        {
            this.PropertyChanged.Invoke(this, new PropertyChangedEventArgs(name));
        }
    }
}
