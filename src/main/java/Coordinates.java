
public class Coordinates {
        private int x;
        private int y;

        public Coordinates(int x, int y) {
                this.x = x;
                this.y = y;
        }

        public int getX() {
                return x;
        }

        public int getY() {
                return y;
        }

        public void setNewCoordinates(int x, int y){
                this.x = x;
                this.y = y;
        }
        public void moveCoordinates(int stepX, int stepY){
                this.x += stepX;
                this.y += stepY;
        }
}
