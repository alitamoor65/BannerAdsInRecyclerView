# BannerAdsInRecyclerView
Show banner ads in recyclerView with multiple sizes
1. Define your data list as following with `Object` type `private List<Object> mRecyclerViewItems = new ArrayList<>();`
2. Define your ads interval `final int adsInterval = 8;`
3. Calculate the size of the new data list after adding your banner ads in the recyler list 
``` 
int contentItemsEvenSize = contentItems.size() % 2 == 0 ? contentItems.size():contentItems.size()+1;
int newLength = contentItems.size() + (contentItemsEvenSize/(adsInterval)); 
```
4. Creat a new updatedfinal data list havving ads
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
5. Now make your banner ads to full width of the screen
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
6. `extend` your `RecyclerAdapter` with `RecyclerView.Adapter<RecyclerView.ViewHolder>` 
7. Override `getItemViewType` in `RecyclerAdapter` class
```
@Override
    public int getItemViewType(int position) {
        if(mRecyclerViewItems.get(position) instanceof AdsDM){
            return 2;
        }
        return 1;
    }
```
8. In `onCreateViewHolder` method check the current item is wether an ad or your content item as follows
```
@NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == 2){
            View adsView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.add_item,viewGroup,false);
            return new AdsViewHolder(adsView);
        }

        View textView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_item,viewGroup,false);
        return new TextViewHolder(textView);
    }
```
9. In `onBindViewHolder` set your current itemView by checking its type
```
if(getItemViewType(i) == 2){
            ((AdsViewHolder)viewHolder).adView.loadAd(((AdsDM) mRecyclerViewItems.get(i)).getAdRequest());
            view = ((AdsViewHolder) viewHolder).adView;
        }else {
            ((TextViewHolder)viewHolder).textView.setText(((TextDM) mRecyclerViewItems.get(i)).getString());
            view = ((TextViewHolder) viewHolder).textView;
        }
```
![Screen Shoot](https://github.com/alitamoor65/BannerAdsInRecyclerView/blob/master/Screenshot_1542174105.png)
