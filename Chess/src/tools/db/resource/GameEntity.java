package tools.db.resource;

public class GameEntity {
    private String event;
    private String site;
    private String date;
    private String round;
    private String white;
    private String black;
    private String result;
    private String nic;
    private String moves;


    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getWhite() {
        return white;
    }

    public void setWhite(String white) {
        this.white = white;
    }

    public String getBlack() {
        return black;
    }

    public void setBlack(String black) {
        this.black = black;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMoves() {
        return moves;
    }

    public void setMoves(String moves) {
        this.moves = moves;
    }

    @Override
    public String toString() {
        return "GameEntity{" +
                "event='" + event + '\'' +
                ", site='" + site + '\'' +
                ", date='" + date + '\'' +
                ", round='" + round + '\'' +
                ", white='" + white + '\'' +
                ", black='" + black + '\'' +
                ", result='" + result + '\'' +
                ", nic='" + nic + '\'' +
                ", game='" + moves + '\'' +
                '}';
    }

}
