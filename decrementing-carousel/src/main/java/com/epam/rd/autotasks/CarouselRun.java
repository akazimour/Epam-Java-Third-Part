package com.epam.rd.autotasks;

public class CarouselRun {

    protected final int[] array = DecrementingCarousel.container.clone();

    int i =0;
    public int next() {
        if (isFinished())
            return -1;
        else {
            while (array[i %= array.length] <= 0) {
                i++;
            }
        }
        return array[i++]--;
    }
    public boolean isFinished() {
        for (int count : array)
            if (count > 0){
                return false;
            }
        return true;
    }

    }


