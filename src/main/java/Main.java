import java.io.*;

/**
 * Description
 *
 * @author bse71
 * Created on 01.12.2021
 * @since
 */
public class Main {

    private static final String STRING_STUB1 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
    private static final String STRING_STUB2 = "абракадабра";
    private static final String STRING_STUB3 = "Обслуживающий персонал в составе поезда. Кто за что отвечает?\n" +
            "Когда мы пользуемся ж/д транспортом, нам оказывает услуги довольно большое количество персонала. Давайте разберемся в его составе и обязанностях. \n" +
            "Работников, которые сопровождают пассажирский поезд и обслуживают пассажиров в пути следования, называют поездной бригадой. Из кого же она состоит? В первую очередь это начальник поезда, проводники, поездной электромеханик и работники вагон-ресторана. Численный состав бригады поезда может различаться в зависимости от количества вагонов и дальности следования, как правило это 20-40 человек.";
    private static final String STRING_STUB4 = "Экспекто Патронум!";
    private static final String BOX_CONTENT_FILENAME = "box.data";

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Нужно ввести либо \"save\", либо \"load\"");
            return;
        }

        switch (args[0]) {
            case "save":    final Box myBox = new Box("Аргентина vs Ямайка", 5, 5.0, "Какая-то неважная строка");
                            save(myBox);
                            break;
            case "load":    final Box box = loadBox();
                            System.out.println(box);
                            break;
            default: System.out.println("Нужно ввести либо \"save\", либо \"load\"");
        }

    }

    private static boolean save(Box box) {
        try (FileOutputStream fos = new FileOutputStream(BOX_CONTENT_FILENAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(box);

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static Box loadBox() {
        try (
                FileInputStream fis = new FileInputStream(BOX_CONTENT_FILENAME);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            final Object obj = ois.readObject();
            Box result = (Box) obj;
            return result;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    private static String readFile(String filename) {
        try (
                FileInputStream fis = new FileInputStream(filename);
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(fis))) {

            StringBuilder stringBuilder = new StringBuilder();
            String tmp;
            while ((tmp = br.readLine()) != null) {
                stringBuilder
                        .append(tmp)
                        .append("\n");
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException err) {
            return "Нет такого файла или доступ к нему запрещен";
        } catch (IOException err) {
            return "Не удалось прочитать файл до конца";
        }
    }

    private static void writeToFile(String filename, String ... content) {
        try (OutputStream fos = new FileOutputStream(filename);
             PrintWriter pw = new PrintWriter(fos)) {

            for (String line : content) {
                pw.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Нет такого файла \"%s\" или доступ к нему запрещен", filename);
        } catch (IOException err) {
            System.out.println("Не удалось записать информацию в файл до конца");
        }
    }
}
