# BannerAdsInRecyclerView
show banner ads in recyclerView with multiple sizes

- Define your data list as following with `Object` type `private List<Object> mRecyclerViewItems = new ArrayList<>();`
- define your ads interval `final int adsInterval = 8;`
- Calculate the size of the new data list after adding your banner ads in the recyler list 
``` 
int contentItemsEvenSize = contentItems.size() % 2 == 0 ? contentItems.size():contentItems.size()+1;
int newLength = contentItems.size() + (contentItemsEvenSize/(adsInterval)); 
```
- creat a new updatedfinal data list havving ads
```
int counter = 0;
        for (int i = 0; i < newLength; i++) {
            if(i % 5 == 0){
                counter+=1;
                mRecyclerViewItems.add(new AdsDM(new AdRequest.Builder().build()));
            }else {
                mRecyclerViewItems.add(contentItems.get(i-counter));
            }
            Log.i("onCreate", "Item["+i+"] = "+ mRecyclerViewItems.get(i).getClass().getSimpleName());
        }
 ```
![alt text](https://github.com/alitamoor65/BannerAdsInRecyclerView/blob/master/Screenshot_1542174105.png)
-now make your banner ads to full width of the screen
```
GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position % (adsInterval +1) == 0){
                    return 2;
                }
                return 1    ;
            }
        });
 ```
