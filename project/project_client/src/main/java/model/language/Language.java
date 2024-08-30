package model.language;

import client.Client;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;

@SuppressWarnings("all")

public class Language implements Observer{

    private static int index = 0;

    private String polygonError;
    private String titleError;
    private String accountWindowTitle;
    private String accountLabel;
    private String nameLabel;
    private String surnameLabel;
    private String nicknameLabel;
    private String testButton;
    private String backButton;
    private String adminWindow;
    private String acceptRequests;
    private String delete;
    private String seeAll;
    private String seePending;
    private String loginMessage;
    private String brushTitle;
    private String menuButton;
    private String requestAccount;
    private String circleData;
    private String circumscribed;
    private String inscribed;
    private String uploadXML;
    private String downloadXML;
    private String loginError;
    private String index1;
    private String name;
    private String surname;
    private String points;
    private String timestamp;
    private String adminAddSuccessful;
    private String adminNoStudent;
    private String done;
    private String somethingWrong;
    private String adminDeleteSuccesful;
    private String accountStatus;
    private String nickname;
    private String area;
    private String perimeter;
    private String dataTitle;
    private String errorNoCircle;
    private String errorNoSave;
    private String circleSaved;
    private String polygonNotCircumscribed;
    private String polygonNotExistent;
    private String polygonNotInscribed;
    private String noFile;
    private String accountNotApproved;
    private String requestSent;
    private String emptyFields;
    private String nameUsed;
    private String optionNotSelected;
    private String loginWindow;
    private String passwordLabel;
    private String loginTitle;
    private String newAccountTitle;
    private String requestTitle;
    private String testTitle;
    private String testStart;
    private String testEnd;
    private String nextButton;
    private String chartButton;

    public String getNextButton(){
        return nextButton;
    }

    public String getTestEnd(){
        return testEnd;
    }

    public String getTestStart(){
        return testStart;
    }

    public String getTestTitle(){
        return testTitle;
    }

    public String getRequestTitle(){
        return requestTitle;
    }

    public String getNewAccountTitle(){
        return newAccountTitle;
    }

    public String getLoginTitle(){
        return loginTitle;
    }

    public String getLoginWindow() {
        return loginWindow;
    }

    public String getPasswordLabel(){
        return passwordLabel;
    }

    public String getPolygonError() {
        return polygonError;
    }

    public String getTitleError() {
        return titleError;
    }

    public String getAccountWindowTitle() {
        return accountWindowTitle;
    }

    public String getAccountLabel() {
        return accountLabel;
    }

    public String getNameLabel() {
        return nameLabel;
    }

    public String getSurnameLabel() {
        return surnameLabel;
    }

    public String getNicknameLabel() {
        return nicknameLabel;
    }

    public String getTestButton() {
        return testButton;
    }

    public String getBackButton() {
        return backButton;
    }

    public String getAdminWindow() {
        return adminWindow;
    }

    public String getAcceptRequests() {
        return acceptRequests;
    }

    public String getDelete() {
        return delete;
    }

    public String getSeeAll() {
        return seeAll;
    }

    public String getSeePending() {
        return seePending;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public String getBrushTitle() {
        return brushTitle;
    }

    public String getMenuButton() {
        return menuButton;
    }

    public String getRequestAccount() {
        return requestAccount;
    }

    public String getCircleData() {
        return circleData;
    }

    public String getCircumscribed() {
        return circumscribed;
    }

    public String getInscribed() {
        return inscribed;
    }

    public String getUploadXML() {
        return uploadXML;
    }

    public String getDownloadXML() {
        return downloadXML;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getIndex1() {
        return index1;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPoints() {
        return points;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getAdminAddSuccessful() {
        return adminAddSuccessful;
    }

    public String getAdminNoStudent() {
        return adminNoStudent;
    }

    public String getDone() {
        return done;
    }

    public String getSomethingWrong() {
        return somethingWrong;
    }

    public String getAdminDeleteSuccesful() {
        return adminDeleteSuccesful;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public String getNickname() {
        return nickname;
    }

    public String getArea() {
        return area;
    }

    public String getPerimeter() {
        return perimeter;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public String getErrorNoCircle() {
        return errorNoCircle;
    }

    public String getErrorNoSave() {
        return errorNoSave;
    }

    public String getCircleSaved() {
        return circleSaved;
    }

    public String getPolygonNotCircumscribed() {
        return polygonNotCircumscribed;
    }

    public String getPolygonNotExistent() {
        return polygonNotExistent;
    }

    public String getPolygonNotInscribed() {
        return polygonNotInscribed;
    }

    public String getNoFile() {
        return noFile;
    }

    public String getAccountNotApproved() {
        return accountNotApproved;
    }

    public String getRequestSent() {
        return requestSent;
    }

    public String getEmptyFields() {
        return emptyFields;
    }

    public String getNameUsed() {
        return nameUsed;
    }

    public String getOptionNotSelected() {
        return optionNotSelected;
    }

    public String getResult() {
        return result;
    }

    public String getResults() {
        return results;
    }

    private String result;
    private String results;

    private Client client;

    public Language(Client client) {
        this.client = client;
        setLanguage();
    }

    public Language(){
        setLanguage();
    }

    private void setLanguage() {

        String excelFilePath = "translation.xlsx";

        Sheet sheet = null;
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            sheet = workbook.getSheetAt(0);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Class<Language> languageClass = Language.class;

        Field[] fields = languageClass.getDeclaredFields();
        for (Field field : fields) {
            if(!field.getName().equals("index")){
                field.setAccessible(true);
                for(int i=1; i<sheet.getLastRowNum()+1; i++){
                    Row row = sheet.getRow(i);
                    Cell cell = row.getCell(0);
                    if(cell.getStringCellValue().equals(field.getName())){
                        Cell cell1 = row.getCell(index+1);
                        try {
                            field.set(this, cell1.getStringCellValue());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void update(int index) {
        this.index = index;

        setLanguage();

        client.sendMessage("UPDATE_LANG " + index);

    }

    public String getchartButton() {
        return chartButton;
    }
}
