package google_api.me;

import com.core.common.Common;
import com.store.model.Store.pojo.Store;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.core.common.Common.PASSWORD;
import static com.core.common.Common.USER;
public class googleapi2 {
    private static int a;

    public static void main(String[] args) throws IOException, BatchUpdateException {
        JSONParser parser = new JSONParser();
        int storen = 0;
        int ins=0;
        int tonext = 0;
        try {
            File place_json = new File("src\\main\\java\\google_api\\store.json");
            FileReader fileReader_place = new FileReader(place_json);
            // Parser returns an object, we need an explicit cast to covert it into a JSONArray
            JSONArray place_array = (JSONArray) parser.parse(fileReader_place);
            JSONObject place = (JSONObject) place_array.get(0);
            JSONArray place_results = (JSONArray) place.get("results");
            //Traverse the list
            for (Object o : place_results) {
                JSONObject obj = (JSONObject) o;
//              google place
                String name = (String) obj.get("name");
                String[] compound_code = ((String) ((JSONObject) obj.get("plus_code")).get("compound_code")).split(" ");
                String city = compound_code[2];
                String community = compound_code[1];
                String vicinity = ((String) obj.get("vicinity")).substring(3);
                JSONObject location = (JSONObject) ((JSONObject) obj.get("geometry")).get("location");
                String loc = "(" + location.get("lat") + "," + location.get("lng") + ")";
//                System.out.println("Name: "+name);
//                System.out.println("city: "+city);
//                System.out.println("community: "+community);
//                System.out.println("vicinity: "+vicinity);
//                System.out.println("Others: "+loc);
//                parseObject(obj);
                String place_id = (String) obj.get("place_id");
//				System.out.println("-----------------------------");
//				System.out.println("讀取google店家資料.....");
                String GOOGLE_URL = "https://maps.googleapis.com/maps/api/place/details/json?"
                        + "place_id=" + place_id + "&"
                        + "language=zh-TW&"
                        + "key=AIzaSyDeePRPPIrCXlI_ETMPHfF7sJS-naoIh9k";
//				System.out.println(GOOGLE_URL);
                URL url = new URL(GOOGLE_URL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setUseCaches(false);
//				System.out.println("寫出IO通道開啟");
                FileWriter fw2 = new FileWriter("src\\main\\java\\google_api\\Details.json");
                BufferedWriter br2 = new BufferedWriter(fw2);
                PrintWriter ps2 = new PrintWriter(br2);
                if (con.getResponseCode() == 200) {
                    InputStream is = con.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
//					System.out.println("開始寫出檔案");
                    String str;
                    ps2.println("[");
                    while ((str = br.readLine()) != null) {
                        ps2.println(str);
                    }
                    ps2.println("]");
                    br.close();
                    isr.close();
                    is.close();
//					System.out.println("完成寫出檔案");
                } else {
                    System.out.println("Failed...");
                }
                ps2.close();
                br2.close();
                fw2.close();
//				System.out.println("寫出IO通道關閉");
//				System.out.println("讀取檔案.....");
                JSONParser parser_details = new JSONParser();
                JSONArray weekday_text = null;
                String phone = null;
                String detail_url = null;
                String website = null;
                try {
                    File details_json = new File("src\\main\\java\\google_api\\Details.json");
                    FileReader fileReader_details = new FileReader(details_json);
                    // Parser returns an object, we need an explicit cast to covert it into a JSONArray
                    JSONArray details_array = (JSONArray) parser_details.parse(fileReader_details);
                    JSONObject details = (JSONObject) details_array.get(0);
                    JSONObject details_results = (JSONObject) details.get("result");
//                    System.out.println("Details:");
                    try {
//                       Place Details
                        JSONObject current_opening_hours = (JSONObject) details_results.get("current_opening_hours");
                        weekday_text = (JSONArray) current_opening_hours.get("weekday_text");
                        phone = ((String) details_results.get("formatted_phone_number")).replace(" ", "");
                        detail_url = (String) details_results.get("url");
                        website = (String) details_results.get("website");
//                        Details_pri(details_results);
                    } catch (NullPointerException e) {
                        System.out.println(name + ":資料不完整");
                    }
//					System.out.println("-------------------------------");
//                    System.out.println("===============================");
                    fileReader_details.close();
                } catch (FileNotFoundException e) {
                    System.out.println(Arrays.toString(e.getStackTrace()) + " :detailsFile Not Found");
                } catch (ParseException e) {
                    System.out.println(Arrays.toString(e.getStackTrace()) + " :Could not details parse");
                } catch (IOException e) {
                    System.out.println(Arrays.toString(e.getStackTrace()) + " :IOException for details");
                }
                try {
                    System.out.println("Name: " + name);
                    System.out.println("city: " + city);
                    System.out.println("community: " + community);
                    System.out.println("vicinity: " + vicinity);
                    System.out.println("Others: " + loc);
                    System.out.println("time: " + weekday_text);
                    System.out.println("phone: " + phone);
                    System.out.println("url: " + detail_url);
                    System.out.println("website: " + website);
                    System.out.println("===============================");
                    storen++;
                    String sql = "select STORE_MAP from cga105g2.store"
                            + " where STORE_MAP like ?";
                    try (Connection connection = DriverManager.getConnection(Common.URL, USER, PASSWORD);
                         PreparedStatement ps3 = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                         //TYPE+SCROLL才可以往回走
                    ) {
                        ps3.setString(1, "%" + loc + "%");
                        ResultSet rs3 = ps3.executeQuery();
                        if (!rs3.next()) {
                            ins++;
                            List<Store> store_1_google = new ArrayList<Store>();
                            store_1_google.add(new Store(name, phone, String.join(",", weekday_text), loc, city, community, vicinity, detail_url, website));
                            insertsoq(store_1_google);
                        } else {
                            tonext++;
                        }
                    }
                    catch (BatchUpdateException e){
                        tonext++;
                        ins--;
                    }
                    catch (SQLException e) {
                        tonext++;
                        ins--;
                    }

                } catch (NullPointerException e) {
                    System.out.println("資料不完整");
                    ins--;
                    tonext++;
                }
            }
            System.out.println("catch到"+storen+"店家");
            System.out.println("inserts"+(ins+a)+"筆資料");
            System.out.println("略過"+(tonext-a)+"筆資料");
            fileReader_place.close();
        } catch (FileNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()) + " :File Not Found");
        } catch (ParseException e) {
            System.out.println(Arrays.toString(e.getStackTrace()) + " :Could not parse");
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()) + " :IOException");
        }
    }
    private static void parseObject(JSONObject obj)  {
        String name = (String)obj.get("name");
        String[] compound_code = ((String)((JSONObject)obj.get("plus_code")).get("compound_code")).split(" ");
        String city=compound_code[2];
        String community=compound_code[1];
        String vicinity = ((String)obj.get("vicinity")).substring(3);
        JSONObject location= (JSONObject) ((JSONObject) obj.get("geometry")).get("location");
        String loc="("+location.get("lat")+","+location.get("lng")+")";
        System.out.println("Name: "+name);
        System.out.println("city: "+city);
        System.out.println("community: "+community);
        System.out.println("vicinity: "+vicinity);
        System.out.println("Others: "+loc);
    }
    private static void Details_pri (JSONObject obj)  {
        JSONObject current_opening_hours= (JSONObject) obj.get("current_opening_hours");
        JSONArray weekday_text= (JSONArray) current_opening_hours.get("weekday_text");
        String phone= ((String) obj.get("formatted_phone_number")).replace(" ", "");
        String detail_url= (String) obj.get("url");
        String website= (String) obj.get("website");

        System.out.println("time: "+weekday_text);
        System.out.println("phone: "+phone);
        System.out.println("url: "+detail_url);
        System.out.println("website: "+website);
    }
    private static void insertsoq(List<Store> store_1_google){
        String sql = "insert into cga105g2.store("
                + "STORE_NAME, STORE_PHONE1, STORE_HOURS,STORE_MAP,STORE_CITY, STORE_DISTRICT, STORE_ADDRESS,STORE_URL,STORE_WEB) "
                + "values(?,?,?,?,?,?,?,?,?);";
        try (Connection connection = DriverManager.getConnection(Common.URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            // 批次新增
            for (Store store : store_1_google) {
                ps.setString(1, store.getStoreName());
                ps.setString(2, store.getStorePhone1());
                ps.setString(3, store.getStoreHours());
                ps.setString(4, store.getStoreMap());
                ps.setString(5, store.getStoreCity());
                ps.setString(6, store.getStoreDistrict());
                ps.setString(7, store.getStoreAddress());
                ps.setString(8, store.getStoreUrl());
                ps.setString(9, store.getStoreWeb());
                ps.addBatch();
            }
            // 取得每個insert成功筆數；Oracle要改用Statement.getUpdateCount()
            int[] counts = ps.executeBatch();
        }
        catch (BatchUpdateException e){
            a--;
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~error");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
