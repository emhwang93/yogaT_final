 package org.techtown.example.expandablelistview;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity implements ExpandableListView.OnChildClickListener {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    //    TextView textView;
    TextView beginner;
    static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandable_list_view1);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        //리스트뷰 기본 아이콘 표시 여부
        expListView.setGroupIndicator(null);
        expListView.setOnChildClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("코스 선택");
        listDataHeader.add("효능별 자세");
        listDataHeader.add("내 통계");

        // Adding child data
        List<String> courseSelection = new ArrayList<String>();
        courseSelection.add("초급");
        courseSelection.add("중급");
        courseSelection.add("고급");
        courseSelection.add("프리 모드");

        List<String> singlePose = new ArrayList<String>();
        singlePose.add("허리 통증");
        singlePose.add("어깨 통증");
        singlePose.add("골반 교정");


        List<String> myData = new ArrayList<String>();
        myData.add("Top 3");
        myData.add("Worst 3");
        myData.add("Before & After");


        listDataChild.put(listDataHeader.get(0), courseSelection); // Header, Child data
        listDataChild.put(listDataHeader.get(1), singlePose);
        listDataChild.put(listDataHeader.get(2), myData);
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
        final String selected = (String) listAdapter.getChild(groupPosition, childPosition);

//        Intent intent;

        switch (selected) {
            case "초급":

                //대화상자 dialog fragment
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("안내");
                builder.setMessage("초급 단계를 실행하시겠습니까?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "예 버튼이 눌렸습니다. ";

                        Intent intent = new Intent(MainActivity.this, YogaPoseCapture.class);
                        startActivity(intent);
                    }
                });
                builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "취소 버튼이 눌렸습니다. ";
//                        textView.setText(message);
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "아니오 버튼이 눌렸습니다. ";
//                        textView.setText(message);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                break;


            case "중급": {
                //대화상자 dialog fragment
                AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);

                builder2.setTitle("안내");
                builder2.setMessage("중급 단계를 실행하시겠습니까?");
                builder2.setIcon(android.R.drawable.ic_dialog_alert);

                builder2.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "예 버튼이 눌렸습니다. ";

                        Intent intent = new Intent(MainActivity.this, YogaPoseCapture.class);
                        startActivity(intent);
                    }
                });
                builder2.setNeutralButton("취소", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "취소 버튼이 눌렸습니다. ";
//                        textView.setText(message);
                    }
                });
                builder2.setNegativeButton("아니오", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "아니오 버튼이 눌렸습니다. ";
//                        textView.setText(message);
                    }
                });

                AlertDialog dialog2 = builder2.create();
                dialog2.show();

            }
            break;


            case "고급": {
                //대화상자 dialog fragment
                AlertDialog.Builder builder3 = new AlertDialog.Builder(MainActivity.this);

                builder3.setTitle("안내");
                builder3.setMessage("고급 단계를 실행하시겠습니까?");
                builder3.setIcon(android.R.drawable.ic_dialog_alert);

                builder3.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "예 버튼이 눌렸습니다. ";

                        Intent intent = new Intent(MainActivity.this, YogaPoseCapture.class);
                        startActivity(intent);
                    }
                });
                builder3.setNeutralButton("취소", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "취소 버튼이 눌렸습니다. ";
//                        textView.setText(message);
                    }
                });
                builder3.setNegativeButton("아니오", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "아니오 버튼이 눌렸습니다. ";
//                        textView.setText(message);
                    }
                });

                AlertDialog dialog3 = builder3.create();
                dialog3.show();

            }
            break;


            case "프리 모드": {
                //대화상자 dialog fragment
                AlertDialog.Builder builder4 = new AlertDialog.Builder(MainActivity.this);

                builder4.setTitle("안내");
                builder4.setMessage("프리 모드를 실행하시겠습니까?");
                builder4.setIcon(android.R.drawable.ic_dialog_alert);

                builder4.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "예 버튼이 눌렸습니다. ";

                        Intent intent = new Intent(MainActivity.this, YogaPoseCapture.class);
                        startActivity(intent);
                    }
                });
                builder4.setNeutralButton("취소", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "취소 버튼이 눌렸습니다. ";
//                        textView.setText(message);
                    }
                });
                builder4.setNegativeButton("아니오", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "아니오 버튼이 눌렸습니다. ";
//                        textView.setText(message);
                    }
                });

                AlertDialog dialog4 = builder4.create();
                dialog4.show();

            }
            break;


            case "허리 통증": {
                //대화상자 dialog fragment
                AlertDialog.Builder builder5 = new AlertDialog.Builder(MainActivity.this);

                builder5.setTitle("안내");
                builder5.setMessage("허리 통증 자세들을 실행하시겠습니까?");
                builder5.setIcon(android.R.drawable.ic_dialog_alert);

                builder5.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "예 버튼이 눌렸습니다. ";

                        Intent intent = new Intent(MainActivity.this, YogaPoseCapture.class);
                        startActivity(intent);
                    }
                });
                builder5.setNeutralButton("취소", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "취소 버튼이 눌렸습니다. ";
//                        textView.setText(message);
                    }
                });
                builder5.setNegativeButton("아니오", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "아니오 버튼이 눌렸습니다. ";
//                        textView.setText(message);
                    }
                });

                AlertDialog dialog5 = builder5.create();
                dialog5.show();
            }
            break;


            case "어깨 통증": {
                //대화상자 dialog fragment
                AlertDialog.Builder builder6 = new AlertDialog.Builder(MainActivity.this);

                builder6.setTitle("안내");
                builder6.setMessage("어깨 통증 자세들을 실행하시겠습니까?");
                builder6.setIcon(android.R.drawable.ic_dialog_alert);

                builder6.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "예 버튼이 눌렸습니다. ";

                        Intent intent = new Intent(MainActivity.this, YogaPoseCapture.class);
                        startActivity(intent);
                    }
                });
                builder6.setNeutralButton("취소", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "취소 버튼이 눌렸습니다. ";
//                        textView.setText(message);
                    }
                });
                builder6.setNegativeButton("아니오", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "아니오 버튼이 눌렸습니다. ";
//                        textView.setText(message);
                    }
                });

                AlertDialog dialog6 = builder6.create();
                dialog6.show();
            }
            break;


            case "골반 교정": {
                //대화상자 dialog fragment
                AlertDialog.Builder builder7 = new AlertDialog.Builder(MainActivity.this);

                builder7.setTitle("안내");
                builder7.setMessage("골반 교정 자세들을 실행하시겠습니까?");
                builder7.setIcon(android.R.drawable.ic_dialog_alert);

                builder7.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "예 버튼이 눌렸습니다. ";

                        Intent intent = new Intent(MainActivity.this, YogaPoseCapture.class);
                        startActivity(intent);
                    }
                });
                builder7.setNeutralButton("취소", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "취소 버튼이 눌렸습니다. ";
//                        textView.setText(message);
                    }
                });
                builder7.setNegativeButton("아니오", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        String message = "아니오 버튼이 눌렸습니다. ";
//                        textView.setText(message);
                    }
                });

                AlertDialog dialog7 = builder7.create();
                dialog7.show();
            }
            break;


            case "Top 3": {
                Intent intent = new Intent(MainActivity.this, Top3.class);
                startActivity(intent);
            }
                break;


            case "Worst 3": {
                Intent intent = new Intent(MainActivity.this, Worst3.class);
                startActivity(intent);
            }
            break;


            case "Before & After": {
                Intent intent = new Intent(MainActivity.this, BeforeAfter.class);
                startActivity(intent);
            }
                break;
            }
            return true;
        }
    }
