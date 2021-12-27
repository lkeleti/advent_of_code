package day21;

public class Player {
    private int position;
    private int score = 0;

    public Player(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position += position;
        this.position = (this.position % 10);
        if (this.position == 0) {
            this.position = 10;
        }
        this.score += this.position;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
