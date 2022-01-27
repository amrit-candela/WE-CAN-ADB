// Note: Before using Phone for automation please ensure that phone has been enabled for Developers Option
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ADBController {
    public static void main(String[] args) {

        ADBController ADBobj = new ADBController();
        System.out.println(ADBobj.getPhoneList());
        ADBobj.openAppInAll("com.candela.wecan");
//        List<String> phone_list = new ArrayList<>();
//        phone_list = getPhoneList();
//        System.out.println(phone_list);
//        streamPhoneScreen("RZ8R82G4F8Y");
//        swipeLeft("RZ8R82G4F8Y");
//        screenShot("RZ8R82G4F8Y");
//        openApp("RZ8R82G4F8Y", "com.candela.wecan");
//        dataInput("RZ8R82G4F8Y");
//        clickEnter("RZ8R82G4F8Y");
//        openAppInAll("com.candela.wecan");
//        grantPermisstion("com.candela.wecan");
    }


    public static List<String> getPhoneList() {
        ArrayList<String> phone_list = new ArrayList<>();
        try {
            Process p = Runtime.getRuntime().exec("adb devices");
//            System.out.println(p.pid());
//            System.out.println(p.exitValue());
            p.getOutputStream().close();
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            input.readLine();
            String line;
            while ((line = input.readLine()) != null) {
                String temp = line.split("\t")[0];
                if (!temp.isEmpty()) {
                    phone_list.add(temp);
                }
            }
//            System.out.println(phone_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phone_list;
    }

    public static void reboot(String deviceName){
        try {
            Process p = Runtime.getRuntime().exec("adb reboot " + deviceName);
//            if (p.exitValue == 0){
//                System.out.println("Reboot Success for device " + deviceName + " !");
//            }else {
//                System.out.println("There was some problem in rebooting phone!");
//            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rebootAll(){
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
//        System.out.println(phone_list);
        for (String deviceName : phone_list ){
            try {
                Process p = Runtime.getRuntime().exec("adb reboot " + deviceName);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void installApp(String appDir, String deviceName){
        try {
            Process p = Runtime.getRuntime().exec("adb -s " + deviceName + "  install -t " + appDir);
            System.out.println("Installation Success!");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void installAppOnAll(String appDir) {
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
        for (String deviceName : phone_list ){
            try {
                Process p = Runtime.getRuntime().exec("adb -s " + deviceName + "  install -t " + appDir);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void uninstallApp(String deviceName, String appName) {
        try {
            Process p = Runtime.getRuntime().exec("adb -s " + deviceName + "  shell pm uninstall " + appName);
            System.out.println("Uninstallation Success! on device "+ deviceName);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void uninstallAppOnAll(String appName) {
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
        for (String deviceName : phone_list ){
            try {
                Process p = Runtime.getRuntime().exec("adb -s " + deviceName + "  shell pm uninstall " + appName);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Uninstallation Success on All Devices!");
    }

    public static void openApp(String deviceName, String appName) {
        try {
            Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " shell monkey -p " + appName + " 1");
            p.waitFor();
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openAppInAll(String appName) {
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
        for (String deviceName : phone_list ){
            try {
                Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " shell monkey -p " + appName + " 1");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("App successfully opened on all devices!");
    }

    public static void grantPermisstion(String appName){
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
        for (String deviceName : phone_list ){
            try {
                Process p1 = Runtime.getRuntime().exec("adb -s " + deviceName + " shell pm grant " + appName + " android.permission.ACCESS_FINE_LOCATION");
                Process p2 = Runtime.getRuntime().exec("adb -s " + deviceName + " shell pm grant " + appName + " android.permission.WRITE_EXTERNAL_STORAGE");
                Process p3 = Runtime.getRuntime().exec("adb -s " + deviceName + " shell pm grant " + appName + " android.permission.READ_EXTERNAL_STORAGE");
//                Process p4 = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input keyevent 0");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Permission granted successfully to all devices!");
    }

    public static void dataInput(String deviceName) {
//        adb shell input keyboard text "Amrit" && adb shell input keyevent 61 && adb shell input keyboard text "192.168.200.11" && adb shell input keyevent 61 && adb shell input keyboard text "testNAME" && adb shell input keyevent 61
        String name = "Amrit";
        String ip = "192.168.200.11";
        String testName = "testName";
//        String cmd = "adb -s " + deviceName + " shell input keyboard text \"" + name + "\" && adb shell input keyevent 61 && " +
//                "adb shell input keyboard text \"" + ip +  "\" && adb shell input keyevent 61 && adb shell input keyboard text \""
//                + testName + "\" && adb shell input keyevent 61";
//        System.out.println(cmd);
        try {
//            String[] command = {"adb", "-s RZ8R82G4F8Y", "shell input keyboard text Amrit"};
            Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input keyboard text "+ name);
            p.waitFor();
            p = Runtime.getRuntime().exec("adb shell input keyevent 61");
            p.waitFor();

            p = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input keyboard text " + ip);
            p.waitFor();
            p = Runtime.getRuntime().exec("adb shell input keyevent 61");
            p.waitFor();

            p = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input keyboard text " + testName);
            p.waitFor();
            p = Runtime.getRuntime().exec("adb shell input keyevent 61");
            p.waitFor();
//            Process p1 = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input keyevent 0");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickEnter(String deviceName) {
        try {
            Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input keyevent 66" );
            Process p1 = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input keyevent 0");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickEnterOnAllPhone() {
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
        for (String deviceName : phone_list ){
            try {
                Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input keyevent 66" );
                Process p1 = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input keyevent 0");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void clickBack(String deviceName) {
        try {
            Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input keyevent 4");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickBackOnAllPhone() {
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
        for (String deviceName : phone_list ){
            try {
                Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input keyevent 4" );
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void clickHome(String deviceName) {
        try {
            Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input keyevent 3");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickHomeOnAllPhone() {
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
        for (String deviceName : phone_list ){
            try {
                Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input keyevent 3" );
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void swipeRight(String deviceName) {
        try {
            Process p = Runtime.getRuntime().exec("adb shell input touchscreen swipe 126 472 413 472");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void swipeRightonAllPhone() {
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
        for (String deviceName : phone_list ){
            try {
                Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input touchscreen " +
                        "swipe 126 472 413 472" );
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void swipeLeft(String deviceName) {
        try {
            Process p = Runtime.getRuntime().exec("adb shell input touchscreen swipe 426 172 113 172");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void swipeLeftonAllPhone() {
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
        for (String deviceName : phone_list ){
            try {
                Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input touchscreen " +
                        "swipe 426 172 113 172" );
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void swipeUp(String deviceName) {
        try {
            Process p = Runtime.getRuntime().exec("adb shell input touchscreen swipe 126 572 126 172");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void swipeUponAllPhone() {
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
        for (String deviceName : phone_list ){
            try {
                Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input touchscreen " +
                        "swipe 126 572 126 172" );
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void swipeDown(String deviceName) {
        try {
            Process p = Runtime.getRuntime().exec("adb shell input touchscreen swipe 126 172 126 572");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void swipeDownonAllPhone() {
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
        for (String deviceName : phone_list ){
            try {
                Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " shell input touchscreen " +
                        "swipe 126 172 126 572" );
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void screenShot(String deviceName) {
        try {
            Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " exec-out screencap -p > shots/" + deviceName + ".png");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void screenShotofAllPhone() {
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
        for (String deviceName : phone_list ){
            try {
                Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " exec-out screencap -p > " +
                        "shots/" + deviceName + ".png");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void streamPhoneScreen(String deviceName) {
        Runtime rt =Runtime.getRuntime();
        String cmd = "adb -s " + deviceName + " exec-out screenrecord --output-format=h264 - | ffplay -framerate 120 -probesize 128 -sync video  -" + "; bash";
        String i[]={"xterm","-e",cmd};
        Process p=null;
        try {
            p=rt.exec(i);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void streamAllPhoneScreen() {
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
        for (String deviceName : phone_list ){
            Runtime rt =Runtime.getRuntime();
            String cmd = "adb -s " + deviceName + " exec-out screenrecord --output-format=h264 - | ffplay -framerate 120 -probesize 128 -sync video  -" + "; bash";
            String i[]={"xterm","-e",cmd};
            Process p=null;
            try {
                p=rt.exec(i);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void unlockPhone(String deviceName) {
        try {
            Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " input keyevent 82");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void lockPhone(String deviceName) {
        try {
            Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " input keyevent 26");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unlockAllPhone() {
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
        for (String deviceName : phone_list ){
            try {
                Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " input keyevent 82");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Successfully Unlocked all devices!");
    }

    public static void lockAllPhone() {
        List<String> phone_list = new ArrayList<>();
        phone_list = getPhoneList();
        for (String deviceName : phone_list ){
            try {
                Process p = Runtime.getRuntime().exec("adb -s " + deviceName + " input keyevent 26");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Successfully Locked all devices!");
    }

}

