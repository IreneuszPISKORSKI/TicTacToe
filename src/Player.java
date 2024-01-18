abstract class Player {
    private int representation;
    private String name;

    private boolean im_ai;

    static Colors_Terminal c_term = new Colors_Terminal();
    public Player(int n, String name){
        this.name = name;
        if (n==0){
            this.representation = 1;
        }else {
            this.representation = 2;
        }
    }
    public int getRepresentation() {
        return representation;
    }
    public String getName() {
        return name;
    }

    public boolean is_Im_ai() {
        return im_ai;
    }

    public void set_Im_ai(boolean im_ai) {
        this.im_ai = im_ai;
    }
    public int get_number(){
        return -55;
    }
}
