package demo.socket.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.buffer.DataBuffer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class Client2 {


    public static byte[] sendRequest(byte[] bodyBuffer,
                                     String ip,
                                     int port,
                                     int timeout) throws IOException {

        Socket socket = null;
        DataOutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            socket = new Socket(ip, port);
            socket.setSoTimeout(timeout * 1000);
            socket.setKeepAlive(true);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = socket.getInputStream();
            long start = System.currentTimeMillis();
            outputStream.write(bodyBuffer);
            outputStream.flush();
            socket.shutdownOutput();
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return bytes;
        } finally {
            if (socket != null) {
                socket.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String requestStr = "INSU80003575    <?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                "\n" +
                "<REQUEST>\n" +
                " <DIST>\n" +
                "  <BANK>SHYH</BANK>\n" +
                "  <ZONE>CN0015000</ZONE>\n" +
                "  <DEPT>CN0010352</DEPT>\n" +
                "  <BankBranchName>..................</BankBranchName>\n" +
                "  <TELLER>-1</TELLER>\n" +
                "  <FINANCIALBANKCODE></FINANCIALBANKCODE>\n" +
                "  <FINANCIALID></FINANCIALID>\n" +
                "  <FINANCIALNAME></FINANCIALNAME>\n" +
                "  <ASK>01</ASK>\n" +
                "  <INSU>5300</INSU>\n" +
                "  <PROC>0</PROC>\n" +
                " </DIST>\n" +
                " <BUSI>\n" +
                "  <CONTENT>\n" +
                "   <MAIN>\n" +
                "    <APPNO></APPNO>\n" +
                "    <APPDATE>20201118</APPDATE>\n" +
                "    <PAYACC>620522001020942134</PAYACC>\n" +
                "    <DELIVER>5</DELIVER>\n" +
                "    <CALL_T>1</CALL_T>\n" +
                "    <SPEC></SPEC>\n" +
                "   </MAIN>\n" +
                "   <TBR>\n" +
                "    <CRSINFO>\n" +
                "     <RVN_TYPE>1</RVN_TYPE>\n" +
                "     <RVN_CITY> </RVN_CITY>\n" +
                "     <RVN_CODE> </RVN_CODE>\n" +
                "     <No_RVN_RSN> </No_RVN_RSN>\n" +
                "     <NOTYET_RVN_RSN> </NOTYET_RVN_RSN>\n" +
                "    </CRSINFO>\n" +
                "    <NAME>....</NAME>\n" +
                "    <SEX>1</SEX>\n" +
                "    <BIRTH>19820910</BIRTH>\n" +
                "    <IDTYPE>1</IDTYPE>\n" +
                "    <IDNO>310106198209100815</IDNO>\n" +
                "    <IDVALIDATE2></IDVALIDATE2>\n" +
                "    <IDVALIDATE>20390822</IDVALIDATE>\n" +
                "    <COUNTRY_CODE></COUNTRY_CODE>\n" +
                "    <ADDR>....................661..51..303..</ADDR>\n" +
                "    <ZIP>200042</ZIP>\n" +
                "    <TEL></TEL>\n" +
                "    <MP>13601991929</MP>\n" +
                "    <LITTLE_MOBILE></LITTLE_MOBILE>\n" +
                "    <EMAIL>13601991929@163.com</EMAIL>\n" +
                "    <BBR_RELA>5</BBR_RELA>\n" +
                "    <Occupation>Y003002</Occupation>\n" +
                "    <education></education>\n" +
                "    <marriage></marriage>\n" +
                "    <IncomeYear>20000000</IncomeYear>\n" +
                "    <Resident>1</Resident>\n" +
                "    <FAMILYINCOME>50000000</FAMILYINCOME>\n" +
                "    <INSURANCECOST>10000000000</INSURANCECOST>\n" +
                "   </TBR>\n" +
                "   <BBR>\n" +
                "    <NAME>....</NAME>\n" +
                "    <SEX>1</SEX>\n" +
                "    <BIRTH>19820910</BIRTH>\n" +
                "    <IDTYPE>1</IDTYPE>\n" +
                "    <IDNO>310106198209100815</IDNO>\n" +
                "    <IDVALIDATE2></IDVALIDATE2>\n" +
                "    <IDVALIDATE>20390822</IDVALIDATE>\n" +
                "    <COUNTRY_CODE></COUNTRY_CODE>\n" +
                "    <ADDR>....................661..51..303..</ADDR>\n" +
                "    <ZIP>200042</ZIP>\n" +
                "    <TEL></TEL>\n" +
                "    <MP>13601991929</MP>\n" +
                "    <LITTLE_MOBILE></LITTLE_MOBILE>\n" +
                "    <EMAIL>13601991929@163.com</EMAIL>\n" +
                "    <Occupation>Y003002</Occupation>\n" +
                "    <education></education>\n" +
                "    <marriage></marriage>\n" +
                "   </BBR>\n" +
                "   <PTS>\n" +
                "    <PT>\n" +
                "     <ID>53000022</ID>\n" +
                "     <UNIT>10</UNIT>\n" +
                "     <CRG_T>1</CRG_T>\n" +
                "     <CRG_Y>0</CRG_Y>\n" +
                "     <COVER_T>2</COVER_T>\n" +
                "     <COVER_Y>5</COVER_Y>\n" +
                "     <DRAW_T></DRAW_T>\n" +
                "     <DRAW_FST></DRAW_FST>\n" +
                "     <DRAW_LST></DRAW_LST>\n" +
                "     <PREMIUM>1000000</PREMIUM>\n" +
                "     <AMNT></AMNT>\n" +
                "     <HLLQ_T></HLLQ_T>\n" +
                "     <INVEST>\n" +
                "      <NVEST_ACC_TYPE></NVEST_ACC_TYPE>\n" +
                "      <INVEST_ACC>\n" +
                "       <ACC_ID></ACC_ID>\n" +
                "       <RATE></RATE>\n" +
                "      </INVEST_ACC>\n" +
                "     </INVEST>\n" +
                "    </PT>\n" +
                "   </PTS>\n" +
                "   <HEALTH>\n" +
                "    <NOTICE></NOTICE>\n" +
                "   </HEALTH>\n" +
                "   <BILL>\n" +
                "    <BILL_T></BILL_T>\n" +
                "    <BILL_USED></BILL_USED>\n" +
                "   </BILL>\n" +
                "   <INFORM1>\n" +
                "    <Health></Health>\n" +
                "    <HealthQ></HealthQ>\n" +
                "    <Occupation></Occupation>\n" +
                "    <OccupationQ></OccupationQ>\n" +
                "    <JuvDieAssured>0</JuvDieAssured>\n" +
                "   </INFORM1>\n" +
                "   <LOAN_INFO>\n" +
                "    <LOAN_BANK></LOAN_BANK>\n" +
                "    <LOAN_INFO_SET>\n" +
                "     <LOAN_BANK>1</LOAN_BANK>\n" +
                "     <LOAN_CONTRACT></LOAN_CONTRACT>\n" +
                "     <LOAN_STARTDATE></LOAN_STARTDATE>\n" +
                "     <LOAN_ENDDATE></LOAN_ENDDATE>\n" +
                "     <LOAN_CONTRACTAMT></LOAN_CONTRACTAMT>\n" +
                "     <LOAN_ADRR></LOAN_ADRR>\n" +
                "    </LOAN_INFO_SET>\n" +
                "   </LOAN_INFO>\n" +
                "   <OTHERS>\n" +
                "    <EFFDATE></EFFDATE>\n" +
                "    <TERMDATE></TERMDATE>\n" +
                "    <PLATENUMBER></PLATENUMBER>\n" +
                "    <SEATS></SEATS>\n" +
                "   </OTHERS>\n" +
                "   <SYR>\n" +
                "    <NAME></NAME>\n" +
                "    <ORDER>1</ORDER>\n" +
                "    <RATIO>0</RATIO>\n" +
                "    <IDTYPE></IDTYPE>\n" +
                "    <IDNO></IDNO>\n" +
                "    <IDVALIDATE></IDVALIDATE>\n" +
                "    <BBR_RELA>Z</BBR_RELA>\n" +
                "    <SEX></SEX>\n" +
                "    <BIRTH></BIRTH>\n" +
                "   </SYR>\n" +
                "  </CONTENT>\n" +
                "  <TRSDATE>20201118</TRSDATE>\n" +
                "  <TRANS>202011180000259222</TRANS>\n" +
                "  <CATE>2</CATE>\n" +
                "  <SUBJECT>1</SUBJECT>\n" +
                " </BUSI>\n" +
                "</REQUEST>\n";
//       byte[]  bys = Client2.sendRequest(requestStr.getBytes(),"www.zdb-dev.com",48090,5000);
        byte[]  bys = Client2.sendRequest(requestStr.getBytes(),"www.zdb-dev.com",48090,5000);
        System.out.println( "return String:"+new String(bys, Charset.forName("GBK")));


    }
}
