import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.*;

class UnionTest {

    @org.junit.jupiter.api.Test
    void union() {
        Union unionTest = new Union(9);

        //this will make sure that its creating the array size as specified by the user
        Assert.assertEquals("The array size does not match what you put in", 9, unionTest.listSize);
        Union unionTest1 = new Union(121);
        Assert.assertEquals("The array size does not match what you put in", 121, unionTest1.listSize);
        Union unionTest2 = new Union(47);
        Assert.assertEquals("The array size does not match what you put in", 47, unionTest2.listSize);
        Union unionTest3 = new Union(0);
        Assert.assertEquals("The array size does not match what you put in", 0, unionTest3.listSize);

        unionTest.union(0, 3);
        //This will make sure that when 0 and 3 put together that 3 becomes the root of 0
        Assert.assertEquals("The root returned is not correct", 3, unionTest.find(0));
        Assert.assertEquals("The root returned is not correct", 3, unionTest.find(3));

        //This will verify that 5 root becomes 6 and then when 5 is unionized with 4 that 4's root also becomes 6
        unionTest.union(5, 6);
        unionTest.union(unionTest.find(5), unionTest.find(4));
        Assert.assertEquals("The root returned is not correct", 6, unionTest.find(5));
        Assert.assertEquals("The root returned is not correct", 6, unionTest.find(4));
        Assert.assertEquals("The root returned is not correct", 6, unionTest.find(6));

        //this will verify that when comparing two sets that the smallest one in size has its root become connected to the root of the larger one
        unionTest.union(unionTest.find(0), unionTest.find(4));
        Assert.assertEquals("You are not changing the root correctly when comparing to other sets", 6, unionTest.find(0));
        Assert.assertEquals("You are not changing the root correctly when comparing to other sets", 6, unionTest.find(4));
        Assert.assertEquals("You are not changing the root correctly when comparing to other sets", 6, unionTest.find(3));
        Assert.assertEquals("You are not changing the root correctly when comparing to other sets", 6, unionTest.find(6));

        //this is just here to verify that when you try to unionize two things that have the same root that nothing happens
        unionTest.union(unionTest.find(0), unionTest.find(4));
        Assert.assertEquals("when two values have the same root its supposed to do nothing", 6, unionTest.find(6));
    }

    @org.junit.jupiter.api.Test
    void find() {
        Union unionTest = new Union(21);

        //this is just to verify that when unionized that the largest one in size becomes the root of the smaller one. If equal then the one on the right becomes the root
        unionTest.union(0, 3);
        unionTest.union(unionTest.find(0), 1);
        unionTest.union(unionTest.find(1), 5);
        unionTest.union(unionTest.find(5), 9);
        unionTest.union(unionTest.find(9), 12);
        Assert.assertEquals("The root returned is not correct", 3, unionTest.find(0));
        Assert.assertEquals("The root returned is not correct", 3, unionTest.find(1));
        Assert.assertEquals("The root returned is not correct", 3, unionTest.find(5));
        Assert.assertEquals("The root returned is not correct", 3, unionTest.find(9));
        Assert.assertEquals("The root returned is not correct", 3, unionTest.find(12));

        //this is just to verify that when unionized that the largest one in size becomes the root of the smaller one. If equal then the one on the right becomes the root
        unionTest.union(6, 7);
        unionTest.union(unionTest.find(6), 20);
        unionTest.union(unionTest.find(6), 19);
        unionTest.union(unionTest.find(6), 18);
        Assert.assertEquals("The root returned is not correct", 7, unionTest.find(6));
        Assert.assertEquals("The root returned is not correct", 7, unionTest.find(20));
        Assert.assertEquals("The root returned is not correct", 7, unionTest.find(19));
        Assert.assertEquals("The root returned is not correct", 7, unionTest.find(18));

        //this will make sure that when thing are being unionized that if number we are performing the find on has a root with a size smaller than the other root that
        //its root becomes the new larger root
        unionTest.union(unionTest.find(5),unionTest.find(7));
        Assert.assertEquals("The root returned is not correct", 3, unionTest.find(5));
        Assert.assertEquals("The root returned is not correct", 3, unionTest.find(3));
        Assert.assertEquals("The root returned is not correct", 3, unionTest.find(18));

    }

    @org.junit.jupiter.api.Test
    void getSize() {
        Union unionTest = new Union(21);

        //this is just to verify that when unionized that the largest one in size becomes the root of the smaller one. If equal then the one on the right becomes the root
        unionTest.union(0, 3);
        unionTest.union(unionTest.find(0), 1);
        unionTest.union(unionTest.find(1), 5);
        unionTest.union(unionTest.find(5), 9);
        unionTest.union(unionTest.find(9), 12);
        Assert.assertEquals("your roots size is not correct", -6, unionTest.getSize(3));

        unionTest.union(6, 7);
        unionTest.union(unionTest.find(6), 20);
        unionTest.union(unionTest.find(6), 19);
        unionTest.union(unionTest.find(6), 18);
        Assert.assertEquals("your roots size is not correct", -5, unionTest.getSize(7));

        //this is just here to verify that the size is added correctly when two roots that have sizes smaller than -1 are added together
        unionTest.union(unionTest.find(5),unionTest.find(7));
        Assert.assertEquals("your roots size is not correct", -11, unionTest.getSize(3));
    }
}