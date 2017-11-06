checkout_docroot:
  file.directory:
    - name: /var/www/html
    - user: www-data
    - group: www-data
    - dir_mode: 775
    - file_mode: 664
    - makedirs: True

rsync:
  pkg.installed:
    - name: rsync
