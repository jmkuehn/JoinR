package com.example.hackaproj.joinr;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.hackaproj.joinr.classes.Event;
import com.example.hackaproj.joinr.classes.User;
import com.example.hackaproj.joinr.classes.UsersDatabaseHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {
	public static UsersDatabaseHelper usersDB;
	User me;
	SharedPreferences prefs;
	Editor editor;
	
	int tempStatus;
	
	Context mContext;
	
    Date strtDt;
    Date endDt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		usersDB = new UsersDatabaseHelper(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		
		//Scanner scanner;
		// PrintWriter writer = new PrintWriter(file);
		//File file = new File(getFilesDir(), "hey.txt");
		
		strtDt = Calendar.getInstance().getTime();
		endDt = Calendar.getInstance().getTime();

		//try {
		//	scanner = new Scanner(getAssets().open(String.format("myData.txt")));
		//} catch (IOException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}

		usersDB.clear();
		me = new User("5133761252", "Josh Kuehn", "Password", 1, new ArrayList<String>(), new Event());
		User rando = new User("3307740777", "Kyle Thompson", "Password", 1, new ArrayList<String>(), new Event());
		rando.setDefaultEvent(new Event("Sleeping", "zzzzzzzzzzzzzzzzzz", 2));
		rando.updateUser();
		
		User rando1 = new User("1234567980", "Bob Bobson", "Password", 1, new ArrayList<String>(), new Event());
		rando1.setDefaultEvent(new Event("Not Sleeping", "I love hackathons!", 2));
		rando1.updateUser();
		
//		User rando2 = new User("0000000001", "George Washington", "Password", 1, new ArrayList<String>(), new ArrayList<Event>());
//		rando2.setDefaultEvent(new Event("Available", "Down to cut cherry trees", 1));
//		rando2.updateUser();
//		
		User rando3 = new User("5134786819", "Chrissy Clyde", "Password", 1, new ArrayList<String>(), new Event());
		rando3.setDefaultEvent(new Event("Presenting", "Demoing my Hackathon project!", 3));
		rando3.updateUser();
//		
		User rando4 = new User("5133904224", "Josh Clyde", "Password", 1, new ArrayList<String>(), new Event());
		rando4.setDefaultEvent(new Event("Good", "Wanna hang?", 1));
		rando4.updateUser();
//		
//		User rando5 = new User("0101010101", "Brutus Buckeye", "Password", 1, new ArrayList<String>(), new Event());
//		rando5.setDefaultEvent(new Event("Camping", "Come camp out at Mirror Lake with me!", 0));
//		rando5.updateUser();
		
		//rando.getEventList().set(0, new Event("Coding", "I hate github sometimes", 2));
		//rando.updateUser();
		
		//me = User.importUserFromDataBase("5133761252");
//		
		me.addFriend(rando.getID());
		me.addFriend(rando1.getID());
		//me.addFriend(rando2.getID());
		me.addFriend(rando3.getID());
		me.addFriend(rando4.getID());
//		me.addFriend(rando5.getID());
//		me.updateUser();
		
//		me.updateEventList();
//		me.setName("Josh Kuehn");
//		me.setID("513-376-1252");
//		me.setColorScheme(0);

//		for (int b = 0; b < 20; b++) {
//			me.addFriend(new User(true));
//		}

		// me.sortFriends();

		LinearLayout myLayout = (LinearLayout) findViewById(R.id.person_linearlayout);

		for (int a = 0; a < me.getFriends().size() + 1; a++) {
			final RelativeLayout view = (RelativeLayout) LayoutInflater.from(
					this).inflate(R.layout.person_layout, null);

			// ((TextView)view.getChildAt(0)).setText(Float.toString(a));
			View separator = LayoutInflater.from(this).inflate(
					R.layout.separator, null);
			view.addView(separator);

			if (a == 0) {
				int[] colors = { Color.WHITE, Color.WHITE,
						me.getColorScheme()[me.getStatusId()] };
				GradientDrawable gradientDrawable = new GradientDrawable(
						GradientDrawable.Orientation.LEFT_RIGHT, colors);

				RelativeLayout temp = (RelativeLayout) ((RelativeLayout) view
						.getChildAt(1)).getChildAt(1);

				//view.getChildAt(1).setVisibility(View.VISIBLE);
				((TextView) ((RelativeLayout) view.getChildAt(0)).getChildAt(0))
						.setText(me.getName());
				((TextView) ((RelativeLayout) view.getChildAt(0)).getChildAt(1))
						.setText(me.idnumToNumber(me.getID()));
				((TextView) ((RelativeLayout) view.getChildAt(0)).getChildAt(2))
						.setText(me.getCurrentEvent().getTitle());
				// view.setBackgroundColor(me.getColorScheme()[me.getStatusId()]);
				// ((TextView) ((RelativeLayout) ((RelativeLayout) view
				// .getChildAt(1)).getChildAt(0)).getChildAt(0))
				// .setText(me.getEventList().get(0).getDescription());
				view.setBackground(gradientDrawable);
				((TextView) view.getChildAt(3)).setText(me.getID());
				((EditText) temp.getChildAt(1)).setText(me.getCurrentEvent().getDescription());
				
				((TextView)((LinearLayout)((RelativeLayout) view.getChildAt(1)).getChildAt(0)).getChildAt(0)).setText(me.getCurrentEvent().getDescription());
				
				LinearLayout availabilityButtons = (LinearLayout)(((RelativeLayout)((RelativeLayout)view.getChildAt(1)).getChildAt(1)).getChildAt(2));
				StateListDrawable mState1 = new StateListDrawable();
				mState1.addState(new int[] { android.R.attr.state_pressed },
				    new ColorDrawable(Color.rgb(Color.red(me.getColorScheme()[0]) / 2, Color.green(me.getColorScheme()[0]) / 2, Color.blue(me.getColorScheme()[0]) / 2)));
				mState1.addState(new int[] { android.R.attr.state_focused },
						new ColorDrawable(me.getColorScheme()[0]));
				mState1.addState(new int[] {},
						new ColorDrawable(me.getColorScheme()[0]));
				((Button)availabilityButtons.getChildAt(0)).setBackgroundDrawable(mState1);
				
				StateListDrawable mState2 = new StateListDrawable();
				mState2.addState(new int[] { android.R.attr.state_pressed },
				    new ColorDrawable(Color.rgb(Color.red(me.getColorScheme()[1]) / 2, Color.green(me.getColorScheme()[1]) / 2, Color.blue(me.getColorScheme()[1]) / 2)));
				mState2.addState(new int[] { android.R.attr.state_focused },
						new ColorDrawable(me.getColorScheme()[1]));
				mState2.addState(new int[] {},
						new ColorDrawable(me.getColorScheme()[1]));
				((Button)availabilityButtons.getChildAt(1)).setBackgroundDrawable(mState2);
				
				StateListDrawable mState3 = new StateListDrawable();
				mState3.addState(new int[] { android.R.attr.state_pressed },
				    new ColorDrawable(Color.rgb(Color.red(me.getColorScheme()[2]) / 2, Color.green(me.getColorScheme()[2]) / 2, Color.blue(me.getColorScheme()[2]) / 2)));
				mState3.addState(new int[] { android.R.attr.state_focused },
						new ColorDrawable(me.getColorScheme()[2]));
				mState3.addState(new int[] {},
						new ColorDrawable(me.getColorScheme()[2]));
				((Button)availabilityButtons.getChildAt(2)).setBackgroundDrawable(mState3);
				
				StateListDrawable mState4 = new StateListDrawable();
				mState4.addState(new int[] { android.R.attr.state_pressed },
				    new ColorDrawable(Color.rgb(Color.red(me.getColorScheme()[3]) / 2, Color.green(me.getColorScheme()[3]) / 2, Color.blue(me.getColorScheme()[3]) / 2)));
				mState4.addState(new int[] { android.R.attr.state_focused },
						new ColorDrawable(me.getColorScheme()[3]));
				mState4.addState(new int[] {},
						new ColorDrawable(me.getColorScheme()[3]));
				((Button)availabilityButtons.getChildAt(3)).setBackgroundDrawable(mState4);
				
				final RelativeLayout userDescEditable = (RelativeLayout)((RelativeLayout)view.getChildAt(1)).getChildAt(1);
				final LinearLayout userDescNoneditable = (LinearLayout)((RelativeLayout)view.getChildAt(1)).getChildAt(0);
				
				((TextView)userDescEditable.getChildAt(0)).setText(me.getCurrentEvent().getDescription());
				

				StateListDrawable mState5 = new StateListDrawable();
				mState5.addState(new int[] { android.R.attr.state_pressed },
				    new ColorDrawable(Color.WHITE));
				mState5.addState(new int[] { android.R.attr.state_focused },
						new ColorDrawable(Color.LTGRAY));
				mState5.addState(new int[] {},
						new ColorDrawable(Color.LTGRAY));
				((Button)((LinearLayout)userDescEditable.getChildAt(3)).getChildAt(1)).setBackground(mState5);
				
				StateListDrawable mState6 = new StateListDrawable();
				mState6.addState(new int[] { android.R.attr.state_pressed },
				    new ColorDrawable(Color.WHITE));
				mState6.addState(new int[] { android.R.attr.state_focused },
						new ColorDrawable(Color.LTGRAY));
				mState6.addState(new int[] {},
						new ColorDrawable(Color.LTGRAY));
				((Button)((LinearLayout)userDescEditable.getChildAt(3)).getChildAt(3)).setBackground(mState6);
				
				StateListDrawable mState7 = new StateListDrawable();
				mState7.addState(new int[] { android.R.attr.state_pressed },
				    new ColorDrawable(Color.WHITE));
				mState7.addState(new int[] { android.R.attr.state_focused },
						new ColorDrawable(Color.LTGRAY));
				mState7.addState(new int[] {},
						new ColorDrawable(Color.LTGRAY));
				((Button)((LinearLayout)userDescEditable.getChildAt(4)).getChildAt(1)).setBackground(mState7);
				
				StateListDrawable mState8 = new StateListDrawable();
				mState8.addState(new int[] { android.R.attr.state_pressed },
				    new ColorDrawable(Color.WHITE));
				mState8.addState(new int[] { android.R.attr.state_focused },
						new ColorDrawable(Color.LTGRAY));
				mState8.addState(new int[] {},
						new ColorDrawable(Color.LTGRAY));
				((Button)((LinearLayout)userDescEditable.getChildAt(4)).getChildAt(3)).setBackground(mState8);
				
				View.OnClickListener onClickListenerEdit = new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						userDescNoneditable.setVisibility(View.GONE);
						((EditText)userDescEditable.getChildAt(0)).setText("");
						((EditText)userDescEditable.getChildAt(1)).setText("");
						expand(userDescEditable);
					}
				};
				
				((Button)((LinearLayout)((RelativeLayout) view.getChildAt(1)).getChildAt(0)).getChildAt(1)).setOnClickListener(onClickListenerEdit);

				View.OnClickListener saveClick = new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String title = ((EditText)userDescEditable.getChildAt(0)).getText().toString();
						String description = ((EditText)userDescEditable.getChildAt(1)).getText().toString();
						((TextView)((RelativeLayout)view.getChildAt(0)).getChildAt(2)).setText(title);
						((TextView)userDescNoneditable.getChildAt(0)).setText(description);
						me.setDefaultEvent(new Event(title, description, tempStatus));
						me.updateUser();
						
						collapse(userDescEditable);
					}
				};
				(((LinearLayout)userDescEditable.getChildAt(5)).getChildAt(0)).setOnClickListener(saveClick);
				
				
				View.OnClickListener cancelClick = new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						collapse(userDescEditable);
						int[] colors = { Color.WHITE, Color.WHITE,
								me.getColorScheme()[me.getStatusId()] };
						GradientDrawable gradientDrawable = new GradientDrawable(
								GradientDrawable.Orientation.LEFT_RIGHT, colors);
						view.setBackground(gradientDrawable);
					}
				};
				(((LinearLayout)userDescEditable.getChildAt(5)).getChildAt(1)).setOnClickListener(cancelClick);
				
				View.OnClickListener joinmeClick = new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						tempStatus = 0;
						int[] colors = { Color.WHITE, Color.WHITE,
								me.getColorScheme()[tempStatus] };
						GradientDrawable gradientDrawable = new GradientDrawable(
								GradientDrawable.Orientation.LEFT_RIGHT, colors);
						view.setBackground(gradientDrawable);
					}
				};
				((Button)availabilityButtons.getChildAt(0)).setOnClickListener(joinmeClick);
				
				View.OnClickListener availableClick = new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						tempStatus = 1;
						int[] colors = { Color.WHITE, Color.WHITE,
								me.getColorScheme()[tempStatus] };
						GradientDrawable gradientDrawable = new GradientDrawable(
								GradientDrawable.Orientation.LEFT_RIGHT, colors);
						view.setBackground(gradientDrawable);
					}
				};
				((Button)availabilityButtons.getChildAt(1)).setOnClickListener(availableClick);
				
				View.OnClickListener busyClick = new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						tempStatus = 2;
						int[] colors = { Color.WHITE, Color.WHITE,
								me.getColorScheme()[tempStatus] };
						GradientDrawable gradientDrawable = new GradientDrawable(
								GradientDrawable.Orientation.LEFT_RIGHT, colors);
						view.setBackground(gradientDrawable);
					}
				};
				((Button)availabilityButtons.getChildAt(2)).setOnClickListener(busyClick);
				
				View.OnClickListener dndClick = new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						tempStatus = 3;
						int[] colors = { Color.WHITE, Color.WHITE,
								me.getColorScheme()[tempStatus] };
						GradientDrawable gradientDrawable = new GradientDrawable(
								GradientDrawable.Orientation.LEFT_RIGHT, colors);
						view.setBackground(gradientDrawable);
					}
				};
				((Button)availabilityButtons.getChildAt(3)).setOnClickListener(dndClick);
				
				View.OnClickListener startDateClick = new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// Process to get Current Date
			            final Calendar c = Calendar.getInstance();
			            int mYear = c.get(Calendar.YEAR);
			            int mMonth = c.get(Calendar.MONTH);
			            int mDay = c.get(Calendar.DAY_OF_MONTH);
			            
			            // Launch Date Picker Dialog
			            DatePickerDialog dpd = new DatePickerDialog(mContext,
			                    new DatePickerDialog.OnDateSetListener() {
			 
			                        @Override
			                        public void onDateSet(DatePicker view, int year,
			                                int monthOfYear, int dayOfMonth) {
			                            // Display Selected date in textbox
			                        	(((TextView)((LinearLayout)(userDescEditable.getChildAt(3))).getChildAt(0))).setText(dayOfMonth + "-"
			                                    + (monthOfYear + 1) + "-" + year);
			                            strtDt.setYear(year);
			                            strtDt.setMonth(monthOfYear);
			                            strtDt.setDate(dayOfMonth);
			                        }
			                    }, mYear, mMonth, mDay);
			            dpd.show();
					}
				};
				(((Button)((LinearLayout)(userDescEditable.getChildAt(3))).getChildAt(1))).setOnClickListener(startDateClick);
				
				
				View.OnClickListener startTimeClick = new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// Process to get Current Date
			            final Calendar c = Calendar.getInstance();
			            int mHour = c.get(Calendar.HOUR_OF_DAY);
			            int mMinute = c.get(Calendar.MINUTE);
			            
			            TimePickerDialog tpd = new TimePickerDialog(mContext,
			                    new TimePickerDialog.OnTimeSetListener() {
			             
			                        @Override
			                        public void onTimeSet(TimePicker view, int hourOfDay,
			                                int minute) {
			                        	(((TextView)((LinearLayout)(userDescEditable.getChildAt(3))).getChildAt(2))).setText(hourOfDay + ":" + minute);
			                        	strtDt.setHours(hourOfDay);
			                        	strtDt.setMinutes(minute);
			                        }
			                    }, mHour, mMinute, false);
			            tpd.show();
					}
				};
				(((Button)((LinearLayout)(userDescEditable.getChildAt(3))).getChildAt(3))).setOnClickListener(startTimeClick);
				
				View.OnClickListener endDateClick = new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// Process to get Current Date
			            final Calendar c = Calendar.getInstance();
			            int mYear = c.get(Calendar.YEAR);
			            int mMonth = c.get(Calendar.MONTH);
			            int mDay = c.get(Calendar.DAY_OF_MONTH);
			            
			            // Launch Date Picker Dialog
			            DatePickerDialog dpd = new DatePickerDialog(mContext,
			                    new DatePickerDialog.OnDateSetListener() {
			 
			                        @Override
			                        public void onDateSet(DatePicker view, int year,
			                                int monthOfYear, int dayOfMonth) {
			                            // Display Selected date in textbox
			                        	(((TextView)((LinearLayout)(userDescEditable.getChildAt(4))).getChildAt(0))).setText(dayOfMonth + "-"
			                                    + (monthOfYear + 1) + "-" + year);
			                            endDt.setYear(year);
			                            endDt.setMonth(monthOfYear);
			                            endDt.setDate(dayOfMonth);
			                            
			                        }
			                    }, mYear, mMonth, mDay);
			            dpd.show();
					}
				};
				(((Button)((LinearLayout)(userDescEditable.getChildAt(4))).getChildAt(1))).setOnClickListener(endDateClick);
				
				
				View.OnClickListener endTimeClick = new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// Process to get Current Date
			            final Calendar c = Calendar.getInstance();
			            int mHour = c.get(Calendar.HOUR_OF_DAY);
			            int mMinute = c.get(Calendar.MINUTE);
			            
			            TimePickerDialog tpd = new TimePickerDialog(mContext,
			                    new TimePickerDialog.OnTimeSetListener() {
			             
			                        @Override
			                        public void onTimeSet(TimePicker view, int hourOfDay,
			                                int minute) {
			                        	(((TextView)((LinearLayout)(userDescEditable.getChildAt(4))).getChildAt(2))).setText(hourOfDay + ":" + minute);
			                        	endDt.setHours(hourOfDay);
			                        	endDt.setMinutes(minute);
			                        }
			                    }, mHour, mMinute, false);
			            tpd.show();
					}
				};
				(((Button)((LinearLayout)(userDescEditable.getChildAt(4))).getChildAt(3))).setOnClickListener(endTimeClick);
				
			} else {
				System.out.println(a - 1 + " " + me.getFriends().get(a - 1));
				User cFriend = User.importUserFromDataBase(me.getFriends().get(a - 1));
				int[] colors = {Color.WHITE,Color.WHITE, me.getColorScheme()[cFriend.getStatusId()] };
				GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);

				RelativeLayout temp = (RelativeLayout) ((RelativeLayout) view.getChildAt(1)).getChildAt(1);

				((TextView) ((RelativeLayout) view.getChildAt(0)).getChildAt(0)).setText(cFriend.getName());
				((TextView) ((RelativeLayout) view.getChildAt(0)).getChildAt(1)).setText(cFriend.idnumToNumber(cFriend.getID()));
				((TextView) ((RelativeLayout) view.getChildAt(0)).getChildAt(2)).setText(cFriend.getCurrentEvent().getTitle());
				// view.setBackgroundColor(me.getColorScheme()[me.getFriends().get(a-1).getStatusId()]);
				((TextView) ((RelativeLayout) ((RelativeLayout) view.getChildAt(1)).getChildAt(2)).getChildAt(0)).setText(cFriend.getCurrentEvent().getDescription());
				view.setBackground(gradientDrawable);
				((TextView) view.getChildAt(3)).setText(cFriend.getID());
				((TextView) temp.getChildAt(0)).setText(cFriend.getCurrentEvent().getDescription());
			}

			View.OnClickListener onClickListener = new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// view.getChildAt(3).setVisibility(View.VISIBLE);

					String id = ((TextView) ((RelativeLayout) v).getChildAt(3))
							.getText().toString();

					RelativeLayout temp = null;

					if (me.getID().equals(id))
					{
						LinearLayout temp2 = (LinearLayout) ((RelativeLayout) view.getChildAt(1)).getChildAt(0);
						temp = (RelativeLayout)((RelativeLayout) view.getChildAt(1)).getChildAt(1);
						if (temp2.getVisibility() == View.VISIBLE)
						{
							collapse(temp2);
						}
						else if (temp.getVisibility() == View.VISIBLE)
						{
							collapse(temp);
							int[] colors = { Color.WHITE, Color.WHITE,
									me.getColorScheme()[me.getStatusId()] };
							GradientDrawable gradientDrawable = new GradientDrawable(
									GradientDrawable.Orientation.LEFT_RIGHT, colors);
							view.setBackground(gradientDrawable);
						}
						else
						{
							expand(temp2);
						}
					}
					else
					{
						
						for (int a = 0; a < me.getFriends().size(); a++)
						{
							User cFriend = User.importUserFromDataBase(me.getFriends().get(a));

							Log.e("FRIEND ID", cFriend.getID());
							if (cFriend.getID().equals(id))
							{
								temp = (RelativeLayout) ((RelativeLayout) view
										.getChildAt(1)).getChildAt(2);
							}
						}
						
						if (temp.getVisibility() == View.GONE) {
							// ((TextView) temp.getChildAt(1)).setText(me
							// .getEventList().get(0).getDescription());
							expand(temp);
						} else {
							collapse(temp);
						}
					}

					//if (temp.getVisibility() == View.GONE) {
						// ((TextView) temp.getChildAt(1)).setText(me
						// .getEventList().get(0).getDescription());
					//	expand(temp);
					//} else {
					//	collapse(temp);
					//}
				}
			};
			view.setOnClickListener(onClickListener);

			myLayout.addView(view);
			// View separator =
			// LayoutInflater.from(this).inflate(R.layout.separator, null);
			// myLayout.addView(separator);
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
		// int id = item.getItemId();
		// if (id == R.id.action_settings) {
		// return true;
		// }
		// return super.onOptionsItemSelected(item);

		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		// Something else
		// case R.id.action_settings:
		// intent = new Intent(this, ThirdActivity.class);
		// startActivity(intent);
		default:
			break;
		}
		return super.onOptionsItemSelected(item);

	}

	public static void expand(final View v) {
		v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		final int targtetHeight = v.getMeasuredHeight();

		v.getLayoutParams().height = 0;
		v.setVisibility(View.VISIBLE);
		Animation a = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime,
					Transformation t) {
				v.getLayoutParams().height = interpolatedTime == 1 ? LayoutParams.WRAP_CONTENT
						: (int) (targtetHeight * interpolatedTime);
				v.requestLayout();
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		// 1dp/ms
		a.setDuration((int) (targtetHeight / v.getContext().getResources()
				.getDisplayMetrics().density));
		v.startAnimation(a);
	}

	public static void collapse(final View v) {
		final int initialHeight = v.getMeasuredHeight();

		Animation a = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime,
					Transformation t) {
				if (interpolatedTime == 1) {
					v.setVisibility(View.GONE);
				} else {
					v.getLayoutParams().height = initialHeight
							- (int) (initialHeight * interpolatedTime);
					v.requestLayout();
				}
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		// 1dp/ms
		a.setDuration((int) (initialHeight / v.getContext().getResources()
				.getDisplayMetrics().density));
		v.startAnimation(a);
	}
}