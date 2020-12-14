package com.jason.lee.learn;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class ValidateCode {
    private static int width = 100;
    private static int height = 35;
    private static int codeCount = 4;
    private static int lineCount = 50;
    private static char[] codeSequence = {'2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    // 验证码
    private String code = null;
    // 验证码图片Buffer
    private BufferedImage buffImg = null;
    private HashMap<String, Object> map = new HashMap<String, Object>();
    private File file = null;

    public ValidateCode() {
        this.createCode();
    }

    public ValidateCode(int width, int height) {
        this.width = width;
        this.height = height;
        this.createCode();
    }

    public ValidateCode(int width, int height, int codeCount, int lineCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        this.createCode();
    }

    private void createCode() {
        int codeX = 0, codeY = 0, fontHeight = 0;
        int red = 0, green = 0, blue = 0;

        codeX = width / (codeCount + 2);//每个字符的宽度(左右各空出一个字符)
        fontHeight = height - 2;//字体的高度
        codeY = height - 4;

        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = buffImg.createGraphics();
        Random random = new Random();
        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // 创建字体
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        g.setFont(font);
        //干扰线
        for (int i = 0; i < lineCount; i++) {
            // 设置随机开始和结束坐标
            int xs = random.nextInt(width);//x坐标开始
            int ys = random.nextInt(height);//y坐标开始
            int xe = xs + random.nextInt(width / 8);//x坐标结束
            int ye = ys + random.nextInt(height / 8);//y坐标结束
            // 产生随机的颜色值，让输出的每个干扰线的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
        }
        // randomCode记录随机产生的验证码
        StringBuffer randomCode = new StringBuffer();
        // 随机产生codeCount个字符的验证码。
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * codeX, codeY);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        code = randomCode.toString();
        map.put("code", code);
        map.put("image", buffImg);
    }

    //校验
    public boolean verifyCode(String input) {
        return input.equalsIgnoreCase(code);
    }

    public String getCode() {
        return code;
    }

    public HashMap<String, Object> getMap() {
        return map;
    }

    public void write(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        this.write(os);
    }

    public void write(OutputStream os) throws IOException {
        ImageIO.write(buffImg, "png", os);
        os.close();
    }

    public static void main(String[] args) throws Exception {
        ValidateCode validateCode = new ValidateCode();
        validateCode.write("C:\\Users\\Administrator\\Desktop\\" + System.currentTimeMillis() + ".png");
        System.out.println("验证码的值为：" + validateCode.getMap().get("code"));
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(validateCode.verifyCode(str));

    }

/*    class FileChooser extends JFrame implements ActionListener
    {
        private static final long serialVersionUID = 3978752082641293497L;
        JButton open=null;

        public FileChooser(){
            open=new JButton("open");
            this.add(open);
            this.setBounds(400, 200, 100, 100);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            open.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {
            JFileChooser jfc=new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
            jfc.showDialog(new JLabel(), "选择");
            file=jfc.getSelectedFile();
            if(file.isDirectory()){
                System.out.println("文件夹:"+file.getAbsolutePath());
            }else if(file.isFile()){
                System.out.println("文件:"+file.getAbsolutePath());
            }
            System.out.println(jfc.getSelectedFile().getName());
        }
    }*/

}

