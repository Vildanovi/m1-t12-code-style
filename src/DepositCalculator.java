import java.util.Scanner;

// Логика отрабатывает верно. Оставил комментарии по некоторым моментам.
public class DepositCalculator {
    /*
    * В методе calculateSimplePercent название параметров были изменены с a, y, d на amount, yearRate, depositPeriod.
    * Возможно стоило бы это учесть и в calculateComplexPercent.
    */
    double calculateComplexPercent(double a, double y, int d) {
        double pay = a * Math.pow((1 + y / 12), 12 * d);
        return makePercent(pay, 2);
    }

    double calculateSimplePercent(double amount, double yearRate, int depositPeriod) {
        return makePercent(amount + amount * yearRate * depositPeriod, 2);
    }

    /*
    * В логике метода makePercent параметр places является степенью числа 10, а далее это же число делится
    * на себя же. А также в calculateComplexPercent и calculateSimplePercent методах, places равен 2.
    * Возможно стоит не передавать places, как параметр метода makePercent и указать в коде метода
    * явное значение places, тоесть 2. Тоесть мы не видим, что оно действительно необходимо, следовательно,
    * мы не плодим функционал, который не используется. Это и относится к самому методу makePercent, так как
    * в итоге он возвращается параметр value, который ему был передан, как параметр. (Вот этот момент я сам не понял
    * как на него реагировать, следовательно, я не решился его убрать)
    */
    double makePercent(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    //Возможно стоило бы назвать этот метод не doImportantJob, а что-то связанное с выбором типа банковского вклада.
    void doImportantJob() {
        int period;
        int action;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сумму вклада в рублях:");
        /*
        * Скорее всего эта переменная yearsDeposit является просто первоначальным вкладом deposit.
        * Просто немного смутило yearsDeposit
        */
        int yearsDeposit = scanner.nextInt();
        System.out.println("Введите срок вклада в годах:");
        period = scanner.nextInt();
        System.out.println("Выберите тип вклада, 1 - вклад с обычным процентом, 2 - вклад с капитализацией:");
        action = scanner.nextInt();
        double out = 0;
        /*
        * Было бы неплохо предусмотреть неверный ввод пользователем: все целочисленные значения, которые неравны 1 и 2.
        * Не был соблюден code style в этом случае if (action == 1) out = calculateSimplePercent(...);
        * (в условие action == 2 это реализованно с соблюдением code style)
        * https://praktikum.notion.site/odeStyle-5980889680f7441dbbe08f4b6d181293 пункт 6.
        * Было бы не лишним предоставить возможность пользователю выбирать и вводить значение ставки(0.06) самому.
        */
        if (action == 1) out = calculateSimplePercent(yearsDeposit, 0.06, period);
        else if (action == 2) {
            out = calculateComplexPercent(yearsDeposit, 0.06, period);
        }
        System.out.println("Результат вклада: " + yearsDeposit + " за " + period + " лет превратятся в " + out);
    }

    public static void main(String[] args) {
        new DepositCalculator().doImportantJob();
    }
}

