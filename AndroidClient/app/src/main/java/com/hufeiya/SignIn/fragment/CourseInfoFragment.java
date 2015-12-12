package com.hufeiya.SignIn.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hufeiya.SignIn.R;
import com.hufeiya.SignIn.jsonObject.JsonCourse;
import com.hufeiya.SignIn.net.AsyncHttpHelper;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseInfoFragment extends Fragment {
    private static final String CID = "param1";
    private String cid;
    private OnFragmentInteractionListener mListener;
    private TextView courseId;
    private TextView techerName;
    private TextView signTime;
    private TextView timesOfWeeks;


    public CourseInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cid is Course Id .
     * @return A new instance of fragment CourseInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseInfoFragment newInstance(String cid) {
        CourseInfoFragment fragment = new CourseInfoFragment();
        Bundle args = new Bundle();
        args.putString(CID, cid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cid = getArguments().getString(CID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_info, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        courseId = (TextView)view.findViewById(R.id.courseId);
        techerName = (TextView)view.findViewById(R.id.teacherName);
        signTime = (TextView)view.findViewById(R.id.signTime);
        timesOfWeeks = (TextView)view.findViewById(R.id.timesOfWeeks);
        fillTextView();
    }

    private void fillTextView(){
        JsonCourse jsonCourse = AsyncHttpHelper.user.getJsonCoursesMap().get(Integer.parseInt(cid));
        courseId.setText(cid);
        techerName.setText(jsonCourse.getTeacherName());
        signTime.setText(jsonCourse.getStartDates());
        //timesOfWeeks.setText(jsonCourse.getNumberOfWeeks());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
