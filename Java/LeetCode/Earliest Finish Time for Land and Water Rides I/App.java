public class App {

    /*
        LeetCode 3633

        Finds the earliest possible time to complete one task from each category, given each task’s earliest start time and duration. The algorithm exhaustively checks every possible pair of tasks and evaluates both execution orders, accounting for any required waiting time between tasks. It tracks the minimum completion time across all combinations and returns the earliest possible finish time.
    */
    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        int[] landStartTime1 = {2,8}; 
        int[] landDuration1 = {4,1}; 
        int[] waterStartTime1 = {6}; 
        int[] waterDuration1 = {3};
        System.out.println("Input: landStartTime = [2,8], landDuration = [4,1], waterStartTime = [6], waterDuration = [3]");
        System.out.println("Output: " +
            findEarliestTime(
                landStartTime1,
                landDuration1,
                waterStartTime1,
                waterDuration1
            )
        );

        int[] landStartTime2 = {5}; 
        int[] landDuration2 = {3}; 
        int[] waterStartTime2 = {1}; 
        int[] waterDuration2 = {10};
        System.out.println("Input: landStartTime = [5], landDuration = [3], waterStartTime = [1], waterDuration = [10]");
        System.out.println("Output: " +
            findEarliestTime(
                landStartTime2,
                landDuration2,
                waterStartTime2,
                waterDuration2
            )
        );
    }

    private int findEarliestTime(
    int[] landStartTime,
    int[] landDuration,
    int[] waterStartTime,
    int[] waterDuration
) {
    int min = Integer.MAX_VALUE;

    for (int i = 0; i < landStartTime.length; i++) {
        for (int k = 0; k < waterStartTime.length; k++) {
            int finishLand = landStartTime[i] + landDuration[i];
            int startWater = Math.max(finishLand, waterStartTime[k]);
            int total1 = startWater + waterDuration[k];
            int finishWater = waterStartTime[k] + waterDuration[k];
            int startLand = Math.max(finishWater, landStartTime[i]);
            int total2 = startLand + landDuration[i];

            min = Math.min(min, Math.min(total1, total2));
        }
    }

    return min;
}

}