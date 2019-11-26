package com.vehiclent.signupActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.vehiclent.R;
import com.vehiclent.base.BaseClass;
import com.vehiclent.mainActivity.HomeActivity;
import com.vehiclent.responseModelClasses.RegisterResponseModel;
import com.vehiclent.responseModelClasses.SocialLoginResponseModel;
import com.vehiclent.signInActivity.SignInActivity;
import com.vehiclent.termServicesActivity.TermConditionsActivity;
import com.vehiclent.utils.CommonMethods;
import com.vehiclent.utils.SavePref;
import com.vehiclent.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends BaseClass implements View.OnClickListener, ISignUpAcitivity {

    SignUpActivity context;

    @BindView(R.id.tv_or)
    TextView tv_or;

    @BindView(R.id.tv_iagree)
    TextView tv_iagree;

    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.edit_firstname)
    EditText edit_firstname;

    @BindView(R.id.edit_lastname)
    EditText edit_lastname;

    @BindView(R.id.edit_email)
    EditText edit_email;

    @BindView(R.id.edit_password)
    EditText edit_password;

    @BindView(R.id.edit_confirm_password)
    EditText edit_confirm_password;

    @BindView(R.id.edit_mobileno)
    EditText edit_mobileno;

    @BindView(R.id.edit_address)
    TextView edit_address;

    @BindView(R.id.tv_gender)
    TextView tv_gender;

    @BindView(R.id.checked)
    CheckBox checked;

    @BindView(R.id.btn_user_signup)
    Button btn_user_signup;

    @BindView(R.id.img_google)
    ImageView img_google;

    @BindView(R.id.img_facebook)
    ImageView img_facebook;

    @BindView(R.id.layout_signup)
    RelativeLayout layout_signup;

    IPSignUp ipSignUp;
    ProgressDialog progressDialog;
    String checked_Value;
    String select_latitude = "", select_longitude = "", email = "", password = "";
    private final int PLACE_PICKER_REQUEST = 1;
    SavePref savePref;

    SignInButton google_login_button;  // google login button
    LoginButton login_button;  // facebook login button
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInOptions googleSignInOptions;
    private static final int RC_SIGN_IN = 0;
    String social_username = "", socail_email = "", socail_id = "", device_token;
    CallbackManager callbackManager;

    //private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this); // facebook sdk initialize
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
        // printKeyHash(this);
        new Runtimepermission(this);
        callbackManager = CallbackManager.Factory.create();
        //Get Firebase auth instance
        //   auth = FirebaseAuth.getInstance();
        Firebase.setAndroidContext(this);
        //   Firebase.setAndroidContext(this.getApplication());


        Initialization();
        EventListner();
    }

    private void Initialization() {

        context = SignUpActivity.this;
        savePref = new SavePref(this);
        progressDialog=new ProgressDialog(context);
        device_token = savePref.getDeviceToken(context, "device_token");
        ipSignUp = new PSignUp(this);
        btn_login.setTypeface(Utility.typeFaceForBoldText(this));
        tv_or.setTypeface(Utility.typeFaceForBoldText(this));
        google_login_button = (SignInButton) findViewById(R.id.google_login_button);
        login_button = (LoginButton) findViewById(R.id.login_button);

        login_button.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);

        facebookLogin();

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

    }

    private void facebookLogin() {

        login_button.setReadPermissions(Arrays.asList("public_profile", "email"));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                progressDialog = Utility.showLoader(SignUpActivity.this);
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken()
                        , new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {


                                try {
                                    socail_email = object.getString("email");
                                    //  savePref.getuser_email(fb_email);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    socail_id = object.getString("id");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    social_username = object.getString("name");
                                    //savePref.setUser_Name(fb_username);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                                SocaialLoginApi(socail_email, social_username, device_token);
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,first_name,last_name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
                progressDialog.dismiss();
            }

            @Override
            public void onCancel() {
                // App code
                progressDialog.dismiss();
                Toast.makeText(context, "Facebook Login Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
               progressDialog.dismiss();
            }

        });
    }

    private void SocaialLoginApi(String socail_email, String social_username, String device_token) {
        progressDialog = Utility.showLoader(SignUpActivity.this);
        ipSignUp.doSocailLogin(socail_email, social_username, device_token);
    }


    private void EventListner() {
        btn_login.setOnClickListener(this);
        btn_user_signup.setOnClickListener(this);
        tv_iagree.setOnClickListener(this);
        tv_gender.setOnClickListener(this);
        edit_address.setOnClickListener(this);
        img_facebook.setOnClickListener(this);
        img_google.setOnClickListener(this);


        checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checked_Value = "1";
                    //    Toast.makeText(context, "checked", Toast.LENGTH_SHORT).show();
                } else {
                    checked_Value = "0";
                    //    Toast.makeText(context, "unchecked", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_login:

                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);

                break;
            case R.id.tv_gender:

                OpenGenderAlertBox();
                break;
            case R.id.img_facebook:
                if (Utility.isNetworkConnected(context)) {
                    login_button.performClick();
                } else {
                    networkErroDialog();
                }

                break;
            case R.id.img_google:
                if (Utility.isNetworkConnected(context)) {
                    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                } else {
                    networkErroDialog();
                }

                break;
            case R.id.edit_address:

                if (Utility.isNetworkConnected(context)) {
                    locationPicker();
                    edit_address.setEnabled(false);
                } else {
                    networkErroDialog();
                }

                break;

            case R.id.tv_iagree:

                Intent intent2 = new Intent(SignUpActivity.this, TermConditionsActivity.class);
                startActivity(intent2);
                break;

            case R.id.btn_user_signup:

                if (Utility.isNetworkConnected(context)) {

                    if (edit_firstname.getText().toString().trim().isEmpty()) {

                        edit_firstname.setError("Please enter first name");
                        edit_firstname.requestFocus();

                    } else if (edit_lastname.getText().toString().trim().isEmpty()) {

                        edit_lastname.setError("Please enter last name");
                        edit_lastname.requestFocus();

                    } else if (tv_gender.getText().toString().equals("Gender") || tv_gender.getText().toString().contains("Gender")) {
                        Snackbar snackbar = Snackbar
                                .make(layout_signup, "Please select your gender", Snackbar.LENGTH_LONG)
                                .setAction("Ok", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Snackbar snackbar1 = Snackbar.make(layout_signup, "", Snackbar.LENGTH_SHORT);
                                        snackbar1.dismiss();
                                    }
                                });

                        snackbar.show();
                      //  Toast.makeText(context, "Please select your gender", Toast.LENGTH_SHORT).show();

                    } else if (edit_email.getText().toString().trim().isEmpty()) {

                        edit_email.setError("Please enter email");
                        edit_email.requestFocus();

                    } else if (!CommonMethods.isValidEmail(edit_email.getText().toString())) {

                        edit_email.setError("Please enter valid email");
                        edit_email.requestFocus();

                    } else if (edit_password.getText().toString().trim().isEmpty()) {

                        edit_password.setError("Please enter password");
                        edit_password.requestFocus();

                    } else if (!CommonMethods.isValidPassword(edit_password.getText().toString().trim())) {

                        edit_password.setError("Password should contain 1 numeric or 1 character or 1 special character");
                        edit_password.requestFocus();

                    } else if (edit_password.getText().toString().trim().length() < 6) {

                        edit_password.setError("Password length should be 6 character or long");
                        edit_password.requestFocus();

                    } else if (edit_confirm_password.getText().toString().trim().isEmpty()) {

                        edit_confirm_password.setError("Password confirmed your password");
                        edit_confirm_password.requestFocus();

                    } else if (!edit_confirm_password.getText().toString().trim().matches(edit_password.getText().toString().trim())) {

                        edit_confirm_password.setError("Password does not match");
                        edit_confirm_password.requestFocus();

                    } else if (edit_mobileno.getText().toString().trim().isEmpty()) {

                        edit_mobileno.setError("Please enter phone number");
                        edit_mobileno.requestFocus();

                    } else if (!CommonMethods.isValidMobile(edit_mobileno.getText().toString())) {

                        edit_mobileno.setError("Please enter valid phone number");
                        edit_mobileno.requestFocus();

                    } else if (edit_address.getText().toString().equals("Address")) {

                       /* edit_address.setError("Please enter address");
                        edit_address.requestFocus();*/

                        Snackbar snackbar = Snackbar
                                .make(layout_signup, "Please enter address", Snackbar.LENGTH_LONG)
                                .setAction("Ok", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Snackbar snackbar1 = Snackbar.make(layout_signup, "", Snackbar.LENGTH_SHORT);
                                        snackbar1.dismiss();
                                    }
                                });

                        snackbar.show();


                    } else if (!checked.isChecked()) {

                        Toast.makeText(context, "Please read the Terms of Services carefully", Toast.LENGTH_SHORT).show();

                    } else {

                        progressDialog = Utility.showLoader(SignUpActivity.this);
                        ipSignUp.doSignUp(edit_firstname.getText().toString().trim(), edit_lastname.getText().toString().trim()
                                , edit_email.getText().toString().trim(), edit_password.getText().toString().trim(),
                                edit_mobileno.getText().toString().trim(), edit_address.getText().toString().trim(), tv_gender.getText().toString().trim());
                    }

                } else {
                    Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                break;
        }

    }

    private void networkErroDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("<font color='#0cb059'>Oops!</font>");
        builder.setMessage("Check your internet connection !!!")
                .setCancelable(false)
                .setPositiveButton("<font color='#0cb059'>OK</font>", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    private void locationPicker() {

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {

            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        edit_address.setEnabled(true);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(context, data);
                Log.e("Tag", "Place: "
                        + place.getAddress()
                        + place.getPhoneNumber()
                        + place.getLatLng().latitude);
                select_latitude = String.valueOf(place.getLatLng().latitude);
                select_longitude = String.valueOf(place.getLatLng().longitude);

                edit_address.setText(place.getAddress().toString());
                //Completed_Address(curent_lat, current_lang);


            }
        } else if (requestCode == 2) {
            if (data != null) {
            }
        } else if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();

            socail_id = acct.getId();
            social_username = acct.getGivenName();
            socail_email = acct.getEmail();
            //  g_profileImage = String.valueOf(acct.getPhotoUrl());

            SocaialLoginApi(socail_email, social_username, device_token);


        } else {
            Toast.makeText(context, "Google Login Cancel", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }


    @Override
    protected void onStop() {
        super.onStop();

        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    private void signOut() {
        if (mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        disconnectFromFacebook();
    }

    private void disconnectFromFacebook() {
        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }

    private void OpenGenderAlertBox() {
        CharSequence[] items = {"Male", "Female"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //  builder.setTitle("Please select gender");
        builder.setTitle(Html.fromHtml("<font color='#0cb059'>Please select your gender!</font>"));
        builder.setPositiveButton(Html.fromHtml("<font color='#0cb059'>OK</font>"), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.dismiss();
            }
        });
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (item == 0) {
                    tv_gender.setText("Male");
                } else if (item == 1) {
                    tv_gender.setText("Female");
                }
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    @Override
    public void onSignUpResponseFromPresenter(int statusValue) {
        progressDialog.dismiss();

    }

    @Override
    public void onSignUpSucessFromPresenter(RegisterResponseModel registerResponseModel) {

        progressDialog.dismiss();
        savePref.setid(registerResponseModel.getData().get(0).getId());

        registerInFirebase();
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Toast.makeText(this, "Registration Sucessfully", Toast.LENGTH_SHORT).show();

    }

    private void registerInFirebase() {

        password = edit_password.getText().toString();

        String url = "https://vehiclent.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Firebase reference = new Firebase("https://vehiclent.firebaseio.com/users");

                if (s.equals("null")) {
                    reference.child(savePref.getid()).child("password").setValue(password);
                    //   Toast.makeText(SignUpActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        JSONObject obj = new JSONObject(s);

                        if (!obj.has(savePref.getid())) {
                            reference.child(savePref.getid()).child("password").setValue(password);
                            //           Toast.makeText(SignUpActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                        } else {
                            //          Toast.makeText(SignUpActivity.this, "username already exists", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(SignUpActivity.this);
        rQueue.add(request);
    }

    @Override
    public void onSignUpFaildeFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSocailLoginFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onSocailLoginSuccessFromPresenter(SocialLoginResponseModel socialLoginResponseModel) {
        progressDialog.dismiss();
        savePref.setid(socialLoginResponseModel.getData().get(0).getId());
        savePref.setfirst_name(socialLoginResponseModel.getData().get(0).getUserName());
        savePref.setuser_email(socialLoginResponseModel.getData().get(0).getEmail());
        savePref.setuser_address(socialLoginResponseModel.getData().get(0).getAddress());
        savePref.setuser_phone_number(socialLoginResponseModel.getData().get(0).getPhoneNumber());

        if (socialLoginResponseModel.getData().get(0).getId().isEmpty() || socialLoginResponseModel.getData().get(0).getId().equals("")) {
            Intent intent = new Intent(SignUpActivity.this, SignUpActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }


    }

    @Override
    public void onSocailLoginFailedFromPresenter(String message) {
        progressDialog.dismiss();
    }

    // hash key for social login google/facebook
    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();
            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);
            Log.e("Package Name=", context.getApplicationContext().getPackageName());
            for (android.content.pm.Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));
                Log.e("Key Hash======", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
        return key;
    }

}
