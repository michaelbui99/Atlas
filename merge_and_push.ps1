<#
    Merges main with specified branch (no fast foward) and returns to the specified branch after push. 
    Use . as value for param CurrentBranch to specify the current git branch
#>

param (
    [Parameter(Mandatory = $true)]
    [string] $CurrentBranch
)

$CheckoutMainCommand = "git checkout main"
$MergeWithBranchCommand = "git merge $CurrenBranch --no-ff"
$PushToMainCommand = "git push"
$ReturnToBranchCommand = "git checkout $CurrentBranch"


if (-not($CurrentBranch)) {
    Throw "Current branch must be specified"
}

if ($CurrentBranch.ToLower -eq "main") {
    Invoke-Expression "git push"
    return
}

if ($CurrentBranch -eq ".") {
    $CurrentBranch = &git rev-parse --abbrev-ref HEAD
}

Invoke-Expression $CheckoutMainCommand

Invoke-Expression "$MergeWithBranchCommand && $PushToMainCommand && $ReturnToBranchCommand"




