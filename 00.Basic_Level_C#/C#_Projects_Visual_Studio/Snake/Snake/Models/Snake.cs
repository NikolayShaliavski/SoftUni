using System.Collections.Generic;

namespace SnakeGame.Models
{
    public class Snake
    {
        public Point Head { get; set; }
        public List<Point> Body { get; set; }

        public Snake()
        {
            this.Body = new List<Point>();
            this.InitSnake();
        }
        public bool Move(Direction direction, Point food, int width, int height)
        {
            bool foodEaten = false;
            Point oldHead = this.Body[this.Body.Count - 1];
            oldHead.Symbol = Constants.SnakeBody;
            int oldX = oldHead.X;
            int oldY = oldHead.Y;

            switch (direction)
            {
                case Direction.Left:
                    oldX--;
                    break;
                case Direction.Up:
                    oldY--;
                    break;
                case Direction.Right:
                    oldX++;
                    break;
                case Direction.Down:
                    oldY++;
                    break;
            }
            this.Head = new Point()
            {
                Symbol = Constants.SnakeHead,
                X = oldX,
                Y = oldY
            };
            this.Body.Add(this.Head);
            if (this.Head.X == food.X &&
                this.Head.Y == food.Y)
            {
                foodEaten = true;
            }
            if (!foodEaten)
            {
                this.Body.RemoveAt(0);
            }
            if (this.Head.X < 0)
            {
                this.Head.X = width - 1;
            }
            else if (this.Head.X >= width)
            {
                this.Head.X = 0;
            }
            else if (this.Head.Y < 0)
            {
                this.Head.Y = height - 1;
            }
            else if (this.Head.Y >= height)
            {
                this.Head.Y = 0;
            }
            return foodEaten;
        }
        public bool IsAlive(int width, int height)
        {
            bool alive = true;
            //if (this.Head.X <= 0 ||
            //    this.Head.X >= width - 1 ||
            //    this.Head.Y <= 0 ||
            //    this.Head.Y >= height -  1)
            //{
            //    alive = false;
            //    return alive;
            //}
            for(int i = 0; i < this.Body.Count - 1; i++)
            {
                Point point = this.Body[i];
                if (this.Head.X == point.X &&
                    this.Head.Y == point.Y)
                {
                    alive = false;
                    return alive;
                }
            }
            return alive;
        }
        public bool ContainsPoint(int x, int y)
        {
            foreach (var point in this.Body)
            {
                if (x == point.X &&
                    y == point.Y)
                {
                    return true;
                }
            }
            return false;
        }
        private void InitSnake()
        {
            this.Head = new Point()
            {
                Symbol = Constants.SnakeHead,
                X = 62,
                Y = 20
            };
            this.Body.Add(new Point()
            {
                Symbol = Constants.SnakeBody,
                X = 55,
                Y = 20
            });
            this.Body.Add(new Point()
            {
                Symbol = Constants.SnakeBody,
                X = 56,
                Y = 20
            });
            this.Body.Add(new Point()
            {
                Symbol = Constants.SnakeBody,
                X = 57,
                Y = 20
            });
            this.Body.Add(new Point()
            {
                Symbol = Constants.SnakeBody,
                X = 58,
                Y = 20
            });
            this.Body.Add(new Point()
            {
                Symbol = Constants.SnakeBody,
                X = 59,
                Y = 20
            });
            this.Body.Add(new Point()
            {
                Symbol = Constants.SnakeBody,
                X = 60,
                Y = 20
            });
            this.Body.Add(new Point()
            {
                Symbol = Constants.SnakeBody,
                X = 61,
                Y = 20
            });
            this.Body.Add(this.Head);
        }
    }
}
