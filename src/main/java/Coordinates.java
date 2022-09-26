
public class Coordinates { //Dopytać Michała czy użyć int czy Integer
        private int x;
        private int y;

        public Coordinates(int x, int y) {
                this.x = x;
                this.y = y;
        }

        public int getX() {
                return x;
        }

        public void setX(int x) {
                this.x = x;
        }

        public int getY() {
                return y;
        }

        public void setY(int y) {
                this.y = y;
        }
        public void setNewCoordinates(int x, int y){
                this.x = x;
                this.y = y;
        }
}
