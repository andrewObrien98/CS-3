import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TestSched {
    /**
     *
     * @param filename name of the file that we will pull from
     * @param task1 array list full of null tasks
     * @param task2 array list full of null tasks
     * @param task3 array list full of null tasks
     * @throws FileNotFoundException
     */
    public static void readTasks(String filename, ArrayList<Task> task1, ArrayList<Task> task2, ArrayList<Task> task3) throws FileNotFoundException {
        int ID = 0;
        Scanner input = new Scanner(new File(filename));
        while(input.hasNext()){//this will get the start time, deadline and duration from the files
            int startTime = input.nextInt();
            int deadline = input.nextInt();
            int minutesReq = input.nextInt();
            ID++;
            task1.add(new Task1(ID, startTime, deadline, minutesReq));
            task2.add(new Task2(ID, startTime, deadline, minutesReq));
            task3.add(new Task3(ID, startTime, deadline, minutesReq));
        }
    }

    /**
     *
     * the string list of files is where we keep the file names that readTasks draws from
     * t1, t2, and t3 correspond to the tasks that they represent
     * the call to s.makeschedule is where we will insert into heap and print the table
     * @throws FileNotFoundException
     */
    public static void main(String args[]) throws FileNotFoundException {
        Scheduler s = new Scheduler();
        String [] files = {"taskset1.txt","taskset2.txt","taskset3.txt","taskset4.txt","taskset5.txt"};//,"taskset2.txt","taskset3.txt","taskset4.txt","taskset5.txt"
        for (String f : files) {
            ArrayList<Task> t1 = new ArrayList();    // elements are Task1
            ArrayList<Task> t2 = new ArrayList();    // elements are Task2
            ArrayList<Task> t3 = new ArrayList();    // elements are Task3
            readTasks(f, t1, t2, t3);//up to here everything is working correctly
            s.makeSchedule("Deadline Priority " + f, t2);
            s.makeSchedule("Startime Priority "+ f, t1);
            s.makeSchedule("Wild and Crazy priority " + f, t3);
       }

    }
}

/**
 * class for scheduling
 */
class Scheduler {
    /**
     *
     * @param typeOfPriority is here so we can print out what type of priority it was
     * @param taskList is either t1, t2, or t3 and full of the tasks
     */
    void makeSchedule(String typeOfPriority, ArrayList<Task> taskList) {
        System.out.println(typeOfPriority);
        LeftistHeap<Task> heap = new LeftistHeap<>();
        taskList.forEach(task -> {//this is where we will insert every task from t1, t2, or t3 into the min leftist heap
            heap.insert(task);
        });
        int totalLate = 0;
        int totalTasksLate = 0;
        int i = 1;
        while(!heap.isEmpty()) {// i is equal to the time, late is how late it is, task is the task that was put into it
            Task task = heap.deleteMin();
            task.duration--;
            if (task.duration != 0) {
                System.out.println("   Time: " + i + " " + task.toString());
                heap.insert(task);
            } else {
                int late = i - task.deadline;
                if (late > 0) {//this is where we print out if its late otherwise we do the else printing
                    System.out.println("   Time: " + i + " " + task.toString() + " **Late " + late);
                    totalTasksLate++;
                    totalLate += late;

                } else {
                    System.out.println("   Time: " + i + " " + task.toString() + " **");
                }
            }
            i++;
        }
        System.out.println("Tasks late " + totalTasksLate + " total minutes late " + totalLate);
        System.out.println("\n\n\n");
    }
}

