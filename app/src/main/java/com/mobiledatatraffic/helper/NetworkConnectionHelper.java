package com.mobiledatatraffic.helper;

import android.os.AsyncTask;

import com.mobiledatatraffic.DataTrafficContract;
import com.mobiledatatraffic.list.DataListViewModel;
import com.mobiledatatraffic.list.DataListViewModelFactory;
import com.mobiledatatraffic.model.MobileData;
import com.mobiledatatraffic.model.MobileDataResponse;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class NetworkConnectionHelper extends AsyncTask<String, Void, List<DataListViewModel>> {

    private final DataTrafficContract.View view;
    private JsonConverter jsonConverter;
    private MobileDataResponseMapper mobileDataResponseMapper;
    private DataListViewModelFactory dataListViewModelFactory;
    private boolean isFailed = false;
    private static final String URL = "https://data.gov.sg/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&limit=59";

    public NetworkConnectionHelper(DataTrafficContract.View view,
                                   JsonConverter jsonConverter,
                                   MobileDataResponseMapper mobileDataResponseMapper,
                                   DataListViewModelFactory dataListViewModelFactory) {
        this.view = view;
        this.jsonConverter = jsonConverter;
        this.mobileDataResponseMapper = mobileDataResponseMapper;
        this.dataListViewModelFactory = dataListViewModelFactory;
    }

    public List<DataListViewModel> doInBackground(String... urls) {
        try {
            java.net.URL obj = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject result = new JSONObject(response.toString());
                List<MobileDataResponse> records = jsonConverter.convert(result);
                List<MobileData> mobileDataList = mobileDataResponseMapper.mapResponse(records);
                return dataListViewModelFactory.generateViewModels(mobileDataList);
            } else {
                isFailed = true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            isFailed = true;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<DataListViewModel> mobileDataList) {
        super.onPostExecute(mobileDataList);
        if (isFailed) {
            view.showError();
        } else {

            view.loadData(mobileDataList);
        }
    }
}
