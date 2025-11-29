void main() throws InterruptedException {

    int loops = 100;
    InfoServer infoServer = new InfoServer();
    // Loop asking for info
    while(loops >= 0) {
        System.out.println("test " + loops);

        // Ask for info
        String[] infoParts = new String[] {"string0", "string1", "string2", "string3", "string4",
                "string5", "string6", "string7", "string8", "string9", "string1", "string1", "string1",
                "string1", "string1", "string1", "string1", "string1", "string1", "string1",
                "string1", "string1", "string1", "string1", "string1", "string1", "string1",
                "string1", "string1", "string1", "string1", "string1", "string1", "string1",
                "string1", "string1", "string1", "string1", "string1", "string1", "string1",
                "string1", "string1", "string1", "string1", "string1", "string1", "string1",
                "string1", "string1", "string1", "string1", "string1", "string1", "string1"};
        Random r = new Random();
        int index = r.nextInt(infoParts.length);
        infoServer.getInfo(infoParts[index]);
        loops--;
    }

}
