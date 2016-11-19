package com.evenuk.evenukapp;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.evenuk.business.Comentario;
import com.evenuk.business.Feed;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.api.model.StringList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private AdapterFeedListView adapterFeedListView;
    private ArrayList<Feed> itens;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser user;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("authStateListener", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("authStateListener", "onAuthStateChanged:signed_out");
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                // ...
            }
        };

        if (user != null) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            //SearchView searchToolBar = (SearchView) toolbar.findViewById(R.id.action_search);
            //searchToolBar.setIconifiedByDefault(true);
            //searchToolBar.setQueryHint(getResources().getString(R.string.hint_search));

            //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            //fab.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View view) {
            //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //                .setAction("Action", null).show();
            //    }
            //});

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            //((TextView) getWindow().findViewById(R.id.user_name_view)).setText(user.getDisplayName());
            View headerView = navigationView.getHeaderView(0);
            ((TextView) headerView.findViewById(R.id.user_email_view)).setText(user.getEmail());

            listView = (ListView) findViewById(R.id.listItemFeed);

            listView.setOnItemClickListener(this);

            createListView();

            /*database = FirebaseDatabase.getInstance();
            Query reference = database.getReference("feeds").limitToLast(10);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot feedSnapshot : dataSnapshot.getChildren()) {
                        Feed feed = feedSnapshot.getValue(Feed.class);
                        Toast.makeText(getApplicationContext(), "The read successful",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "The read failed: " + databaseError.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });*/
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    private void createListView() {
        itens = new ArrayList<Feed>();
        itens.add(new Feed(1, "Danilo Avilez", "Estilo Night", new Date(), "Lorem ipsum not have tilt", new ArrayList<Comentario>(), new ArrayList<String>()));
        itens.add(new Feed(2, "Adriel Andrade", "Estilo Tanga", new Date(), "Lorem ipsum not have tilt", new ArrayList<Comentario>(), new ArrayList<String>()));

        adapterFeedListView = new AdapterFeedListView(this, itens);

        listView.setAdapter(adapterFeedListView);

        listView.setCacheColorHint(Color.TRANSPARENT);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            // Handle settings
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Feed item = (Feed) adapterFeedListView.getItem(i);

        Toast.makeText(getApplicationContext(), "Você clicou em: " + item.getTituloEvento(), Toast.LENGTH_SHORT).show();
    }
}