using System;

namespace _08.ConnectedAreasInMatrix
{
    public class Area : IComparable<Area>
    {
        private int startRow;
        private int startCol;
        private int cells;

        public Area(int row, int col, int cells)
        {
            this.startRow = row;
            this.startCol = col;
            this.cells = cells;
        }

        public int Row
        {
            get
            {
                return startRow;
            }
        }

        public int Col
        {
            get
            {
                return startCol;
            }
        }

        public int Cells
        {
            get
            {
                return cells;
            }
        }

        public int CompareTo(Area otherArea)
        {
            int result = otherArea.Cells.CompareTo(this.Cells);
            if (result == 0)
            {
                result = this.Row.CompareTo(otherArea.Row);
            }
            if (result == 0)
            {
                result = this.Col.CompareTo(otherArea.Col);
            }
            return result;
        }
    }
}
