/**
 * for task1 we will have the earliest startTime be the priority and earliest deadline if tied
 */
public class Task1 extends Task {
    public Task1(int ID, int start, int deadline, int duration) {
        super(ID,start,deadline,duration);
    }
    @Override
    public int compareTo(Task t2) {//I know right here I didnt leave anything up to chance if they tied but I think in a real world a person wouldnt want to
         if (start < t2.start) return -1;
         if(start == t2.start && deadline < t2.deadline) return -1;
         if(start == t2.start && deadline == t2.deadline && duration > t2.duration) return -1;
         return 1;
      }

}

/**
 * For task 2 we will have earliest deadline be the priority, I added a couple of things in case there are ties
 */
 class Task2 extends Task {
     public Task2(int ID, int start, int deadline, int duration) {
         super(ID,start,deadline,duration);
     }
     @Override
     public int compareTo(Task t2) {//I know right here I didnt leave anything up to chance if they tied but I think in a real world a person wouldnt want to
         if(deadline < t2.deadline)return -1;
         if(deadline == t2.deadline && start < t2.start) return -1;
         if(deadline == t2.deadline && start == t2.start && duration > t2.duration) return -1;
         return 1;
     }
 }

/**
 * my choice to pick the priority
 */
class Task3 extends Task {
    public Task3(int ID, int start, int deadline, int duration) {
        super(ID,start,deadline,duration);
    }
    @Override
    public int compareTo(Task t2) {
        if(duration > t2.duration)return -1;
        if(duration == t2.duration && start < t2.start)return -1;
        if(duration == t2.duration && start == t2.start && deadline < t2.deadline)return -1;
        return 1;
    }
}