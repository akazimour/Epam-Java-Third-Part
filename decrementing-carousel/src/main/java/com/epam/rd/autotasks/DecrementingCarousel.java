package com.epam.rd.autotasks;

public class DecrementingCarousel {
    private int capacity;
    static int[] container;
    private int counter = 0;
    boolean isRun;
    {
        isRun = false;
    }

    public DecrementingCarousel(int capacity) {
        this.capacity = capacity;
        container = new int[capacity];
    }

    public boolean addElement(int element){
      if (element > 0 && counter < capacity && !isRun){
          container[counter++] = element;
          return true;
      }
      return false;
    }
    public CarouselRun run(){
        if (!isRun) {
            isRun = true;
            return new CarouselRun();
        }
        return null;
    }
}
