public class Fenwick {
    private int[] arr;

    public void build(int[] arr) {
        int n = arr.length;
        this.arr = new int[n];
        for (int i = 0; i < n; i++) {
            this.arr[i] += arr[i];
            int j = i | (i + 1);
            if (j < n) {
                this.arr[j] += this.arr[i];
            }
        }
    }

    public void update(int index, int new_elem, int[] array) {
        int delta = new_elem - array[index];
        array[index] = new_elem;
        int i = index;
        while (i < arr.length) {
            arr[i] += delta;
            i = i | (i + 1);
        }
    }

    public int prefixSum(int index) {
        int res = 0;
        int temp = index;
        while (temp >= 0) {
            res += arr[temp];
            temp = (temp & (temp + 1)) - 1;
        }
        return res;
    }

    public int rangeSum(int left, int right) {
        return (prefixSum(right) - prefixSum(left - 1));
    }

    public void printTree() {
        System.out.print("Дерево Фэнвика: ");
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
