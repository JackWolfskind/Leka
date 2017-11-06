base:
  'frontend.*.local':
    - sls.php.runtime.websites.apache
    - sls.php.runtime.websites.php
    - sls.php.runtime.deploy

