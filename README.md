# Introduction
This is project to store, filter and present collection of items.
The collection item implements Item interface.

The project is in-memory, without using DB.

## Resource data.
Stored as .tsv file.
PostcardHelper translate .tsv file row into entity.

### File structure
- ID (String, YYYYMMDDCCN). If item has the same date and country add number at the end. Example 20230428CH or 20230428CH2
- Image	(String, YYYY_MM_DD CC *.jpg), image file name. If there more than one image per item, them name should be in format YYYY_MM_DD CC ***1**.jpg
- Sent (String, DD.MM.YYYY) Date item sent.
- Received (String, DD.MM.YYYY) Date item received.
- Height (int). Height in millimeters.
- Width	(int). Width in millimeters.
- Country (String, CC). Country sent from.
- City (String). City sent from.
- Sender (String). SenderId.
- Description (String). Allowed formatting: '|' for a new line, [Page name|Page URL] for URL. Example: [Landesmuseum|https://www.landesmuseum.ch/en]
- Tags (String), tagIds separated by comma (,)
- Images (int). Count of images belonging to item.
- Origin country (String CC, not used). Origin country of item.

where:
- CC - ISO 3166-1 alpha-2 country code
- N - number
