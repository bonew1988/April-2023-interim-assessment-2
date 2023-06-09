package toydraw;

import java.util.ArrayList;
import java.util.Random;

import toydraw.Toys.Ball;
import toydraw.Toys.Car;
import toydraw.Toys.Doll;
import toydraw.Toys.Gun;
import toydraw.Toys.Soldier;
import toydraw.Toys.Toy;

public class PrizeBox {
    private ArrayList<Toy> inside;

    public PrizeBox() {
        this.inside = new ArrayList<>();
    }

    public ArrayList<Toy> getInside() {
        return inside;
    }

    public void addToy(Toy toy) {

        if (toy.getQuantity() == 0) {

            System.out.println("Таких игрушек больше нет");
        } else {
            inside.add(toy);
            toy.downQuantity();
            System.out.println("Добавили" + toy.getTitle());
        }
    }

    public void removeItem(int index) {
        if (this.inside.size() == 0) {
            System.out.println("На витрине нет игрушек");
            return;
        }
        this.inside.remove(index);
    }

    public void clearBox() {
        this.inside.clear();
    }

    public String printBox() {
        String out = "На витрине лежит\n______________________________________________________________";

        for (int i = 0; i < this.inside.size() - 3; i += 3) {
            out = out + "\n";
            for (int j = i; j < i + 3; j++) {
                out = out + this.inside.get(j).getTitle() + "|";
            }

            out = out + "\n";
        }
        out = out + "______________________________________________________________";
        return out;
    }

    public int dropPrize() {
        Random random = new Random();
        Integer[] firstDrop = new Integer[(int) (1 + this.inside.size() / 5)];
        // первый выбор элементов случаен
        if (this.inside.size() == 0) {
            return -1;
        }
        for (int i = 0; i < firstDrop.length; i++) {
            if (firstDrop.length > 1) {
                firstDrop[i] = random.nextInt(0, (this.inside.size() - 1));
            } else {
                firstDrop[0] = random.nextInt(this.inside.size());
            }

        }
        // нахождение наибольшего веса после отбора сравнением
        int maxIndex = firstDrop[0];
        int maxValue = this.inside.get(maxIndex).getChance();
        this.inside.get(firstDrop[0]).getChance();
        for (int i = 0; i < firstDrop.length; i++) {
            if (this.inside.get(firstDrop[i]).getChance() > maxValue) {
                maxValue = this.inside.get(firstDrop[i]).getChance();
                maxIndex = firstDrop[i];
            } else if (this.inside.get(firstDrop[i]).getChance() == maxValue) {
                if (random.nextBoolean()) {
                    maxIndex = firstDrop[i];
                    maxValue = this.inside.get(firstDrop[i]).getChance();
                }
            }
        }

        return maxIndex;
    }

    public void FillBox() {
        this.inside.clear();
        int randToy;
        Random random = new Random();
        for (int i = 0; i < 11; i++) {
            randToy = random.nextInt(1, 6);
            switch (randToy) {
                case 1:
                    this.inside.add(new Car());
                    break;
                case 2:
                    this.inside.add(new Doll());
                    break;

                case 3:
                    this.inside.add(new Soldier());
                    break;
                case 4:
                    this.inside.add(new Ball());
                    break;
                case 5:
                    this.inside.add(new Gun());
                    break;

            }
        }
        System.out.println(this.printBox());
    }

}
